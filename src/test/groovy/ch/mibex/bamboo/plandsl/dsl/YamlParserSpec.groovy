package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.branches.AutoBranchManagement
import ch.mibex.bamboo.plandsl.dsl.branches.Branch
import ch.mibex.bamboo.plandsl.dsl.branches.BranchMerging
import ch.mibex.bamboo.plandsl.dsl.branches.GateKeeper
import ch.mibex.bamboo.plandsl.dsl.deployprojs.DeploymentProject
import ch.mibex.bamboo.plandsl.dsl.deployprojs.Environment
import ch.mibex.bamboo.plandsl.dsl.deployprojs.ReleaseVersioning
import ch.mibex.bamboo.plandsl.dsl.jobs.ArtifactDefinition
import ch.mibex.bamboo.plandsl.dsl.jobs.ArtifactDependency
import ch.mibex.bamboo.plandsl.dsl.notifications.EmailNotification
import ch.mibex.bamboo.plandsl.dsl.notifications.Notifications
import ch.mibex.bamboo.plandsl.dsl.permissions.PermissionTypes
import ch.mibex.bamboo.plandsl.dsl.permissions.Permissions
import ch.mibex.bamboo.plandsl.dsl.scm.ScmBitbucketCloud
import ch.mibex.bamboo.plandsl.dsl.scm.ScmBitbucketGit
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import ch.mibex.bamboo.plandsl.dsl.tasks.CustomTask
import ch.mibex.bamboo.plandsl.dsl.tasks.InlineScript
import ch.mibex.bamboo.plandsl.dsl.tasks.ScriptTask
import ch.mibex.bamboo.plandsl.dsl.tasks.Tasks
import ch.mibex.bamboo.plandsl.dsl.triggers.BitbucketServerTrigger
import ch.mibex.bamboo.plandsl.dsl.triggers.ScheduledTrigger
import ch.mibex.bamboo.plandsl.dsl.triggers.Triggers
import ch.mibex.bamboo.plandsl.dsl.variables.Variable
import ch.mibex.bamboo.plandsl.dsl.variables.Variables
import spock.lang.Specification

class YamlParserSpec extends Specification {

    def 'empty YAML'() {
        setup:
        def yamlParser = new YamlParser(new NullBambooFacade())

        when:
        def dsl = yamlParser.parseYaml('')

        then:
        dsl != null
    }

    def 'simple plan'() {
        setup:
        def yamlParser = new YamlParser(new NullBambooFacade())

        when:
        def yaml = yamlParser.parseYaml(getClass().getResource('/yaml/SimplePlan.yml').text)

        then:
        yaml.project.key == 'MYPROJECT'
        yaml.project.name == 'This is my project'
        yaml.project.plans[0].key == 'MYPLAN'
        yaml.project.plans[0].name == 'my plan'
    }

    def 'full YAML'() {
        setup:
        def yamlParser = new YamlParser(new NullBambooFacade(['mykey': 'myvalue', 'mykey2': 'myvalue2']))

        when:
        def yaml = yamlParser.parseYaml(getClass().getResource('/yaml/Full.yml').text)

        then:
        yaml.project.key == 'MYPROJECT'
        yaml.project.name == 'This is my project'
        yaml.project.plans[0].key == 'MYPLAN'
        yaml.project.plans[0].name == 'my plan'
        yaml.project.plans[0].stages[0].name == 'mystage'
        yaml.project.plans[0].stages[0].jobs[0].key == 'MYJOB'
        yaml.project.plans[0].stages[0].jobs[0].name == 'myjob'
        yaml.project.plans[0].stages[0].jobs[0].tasks.tasks[0] == new ScriptTask(
                inlineScript: new InlineScript(scriptBody: 'myvalue')
        )
        yaml.project.plans[0].stages[0].jobs[0].tasks.tasks[1] == new CustomTask(
                pluginKey: 'ch.mibex.bamboo.plandsl',
                taskConfig: ['key1': 'value1', 'key2': 'value2']
        )
        yaml.project.plans[0].stages[0].jobs[0].artifactDependencies[0] == new ArtifactDependency(
                name: 'myExe',
                destinationDirectory: 'targets'
        )
        yaml.project.plans[0].stages[0].jobs[0].artifactDefinitions[0] == new ArtifactDefinition(
                location: 'mydir',
                copyPattern: '*.exe',
                isShared: 'true',
                name:  'def'
        )
        yaml.project.plans[0].scm.scms[0] == new ScmBitbucketCloud(
                repoSlug: 'repo1',
                branch: 'master',
                scmType: new ScmBitbucketGit(),
                authType: new PasswordAuth(userName: 'user', password: 'pw')
        )
        yaml.project.plans[0].notifications.notifications[0] == new EmailNotification(
                notificationRecipientType: EmailNotification.NOTIFICATION_RECIPIENT_TYPE,
                event: Notifications.NotificationEvent.ALL_BUILDS_COMPLETED,
                address: 'test@test.com'
        )
        yaml.project.plans[0].triggers.triggers[0] == new ScheduledTrigger(
                cronExpression: '0 0 0 ? * *',
                description: 'run every day at noon'
        )
        yaml.project.plans[0].branches.branches[0] == new Branch(
                name: 'feature_1234',
                vcsBranchName: 'feature/1234',
                cleanupAutomatically: true,
                description: 'my important feature branch',
                triggers: new Triggers(triggers: [new BitbucketServerTrigger(description: 'run when push')])
        )
        yaml.project.plans[0].branches.autoBranchManagement == new AutoBranchManagement(
                deletePlanBranchesAfterDays: 2,
                deletedBranchesStrategy: AutoBranchManagement.DeletedBranchesStrategy.DO_NOT_DELETE_PLAN_BRANCHES,
                inactiveBranchesStrategy: AutoBranchManagement.InactiveBranchesStrategy.DELETE_INACTIVE_PLAN_BRANCHES_AFTER_DAYS,
                deleteInactivePlanBranchesAfterDays: 3,
                newBranchesStrategy: AutoBranchManagement.NewBranchesStrategy.NEW_PLAN_BRANCHES_FOR_MATCHING_BRANCH_NAMES,
                matchingBranchesRegex: '.*'
        )
        yaml.project.plans[0].branches.branchMerging == new BranchMerging(
                mergeStrategy: new GateKeeper(
                        planBranchKey: 'PLANKEY',
                        pushEnabled: false
                )
        )
        yaml.project.plans[0].deploymentProjects[0] == new DeploymentProject(
                id: 1,
                name: 'DP1',
                description: 'my deployment project',
                useCustomPlanBranch: 'develop',
                releaseVersioning: new ReleaseVersioning(
                        nextReleaseVersion: '1.0.0',
                        autoIncrement: true,
                        variables: ['var1', 'var2']
                ),
                permissions: new Permissions(
                  user: [
                          'bob': PermissionTypes.PermissionType.VIEW,
                          'carol': PermissionTypes.PermissionType.EDIT
                  ],
                  group: [
                          'devops': PermissionTypes.PermissionType.ADMIN
                  ],
                  other: [
                          (Permissions.OtherUserType.LOGGED_IN_USERS): PermissionTypes.PermissionType.VIEW
                  ]
                ),
                environments: [
                        new Environment(
                            name: 'staging',
                            id: 1,
                            description: 'Staging env',
                        ),
                        new Environment(
                            name: 'prod',
                            id: 2,
                            description: 'Production env',
                            tasks: new Tasks(
                                   tasks: [new ScriptTask(
                                           inlineScript: new InlineScript(scriptBody: 'myvalue2')
                                   )]
                            ),
                            variables: new Variables(
                                    variables: [
                                            new Variable('myKey1', 'myValue1'),
                                            new Variable('myKey2', 'myValue2')
                                    ]
                            )
                        )
                ]
        )
    }

//    def 'multiple projects'() {
//        setup:
//        def yamlParser = new YamlParser(new NullBambooFacade())
//
//        when:
//        def projects = yamlParser.parseYaml(
//                             """project:
//                                |  key: MYPROJECT1
//                                |  name: This is my project 1
//                                |project:
//                                |  key: MYPROJECT2
//                                |  name: This is my project 2""".stripMargin())
//
//        then:
//        projects[0].key == 'MYPROJECT1'
//        projects[0].name == 'This is my project 1'
//        projects[1].key == 'MYPROJECT2'
//        projects[1].name == 'This is my project 2'
//    }
}