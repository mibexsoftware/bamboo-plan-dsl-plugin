package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import ch.mibex.bamboo.plandsl.dsl.notifications.EnvironmentNotifications
import ch.mibex.bamboo.plandsl.dsl.notifications.HipChatNotification
import ch.mibex.bamboo.plandsl.dsl.notifications.Notifications
import ch.mibex.bamboo.plandsl.dsl.permissions.PermissionTypes
import ch.mibex.bamboo.plandsl.dsl.permissions.Permissions
import ch.mibex.bamboo.plandsl.dsl.tasks.CommandTask
import ch.mibex.bamboo.plandsl.dsl.tasks.InjectBambooVariablesTask
import ch.mibex.bamboo.plandsl.dsl.tasks.Tasks
import ch.mibex.bamboo.plandsl.dsl.variables.Variable
import ch.mibex.bamboo.plandsl.dsl.variables.Variables
import spock.lang.Specification

class DeploymentProjectSpec extends Specification {

    def 'deployment projects'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/deployprojs/DeploymentProject.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].deploymentProjects[0] == new DeploymentProject(name: "dp2", id: 59899906)
        results.projects[0].plans[0].deploymentProjects[1] == new DeploymentProject(name: "dp3", id: 59899907)
        results.projects[0].plans[0].deploymentProjects[2] == new DeploymentProject(
                name: "dp1",
                description: "desc1",
                useCustomPlanBranch: "develop",
                environments: [new Environment(
                        name: "env1",
                        description: "desc",
                        tasks: new Tasks(tasks: [
                                new CommandTask(
                                        description: "run command",
                                        enabled: true,
                                        isFinal: false,
                                        workingSubDirectory: ".",
                                        argument: "-n",
                                        environmentVariables: "what=EVER",
                                        executable: "atlas-clean"
                                ),
                                new InjectBambooVariablesTask(
                                        enabled: true,
                                        isFinal: true,
                                        description: "Inject Build Variables",
                                        propertiesFilePath: "envVars.properties",
                                        variablesScope: InjectBambooVariablesTask.VariablesScope.RESULT,
                                        namespace: "soulmv"
                                )
                        ])
                )],
                releaseVersioning: new ReleaseVersioning(
                        nextReleaseVersion: "1.0-m1",
                        autoIncrement: true,
                        applicableToBranches: true,
                        variables: ["test1", "test2"]
                ),
                permissions:  new Permissions(
                        user: ['diego': [PermissionTypes.PermissionType.VIEW]],
                        group: ['devops': [PermissionTypes.PermissionType.VIEW]],
                        other: [(Permissions.OtherUserType.LOGGED_IN_USERS): [PermissionTypes.PermissionType.VIEW, PermissionTypes.PermissionType.EDIT]],
                )
        )
        results.projects[0].plans[0].deploymentProjects[3] == new DeploymentProject(
                name: "dp2",
                description: "desc1",
                useCustomPlanBranch: "release",
                environments: [new Environment(
                        name: "env1",
                        description: "desc1"
                ), new Environment(
                        name: "env2",
                        description: "desc2",
                        variables: new Variables(variables: [new Variable("key1", "value1"), new Variable("key2", "value2")]),
                        notifications: new EnvironmentNotifications(notifications: [new HipChatNotification(
                                notificationRecipientType: "com.atlassian.bamboo.plugins.bamboo-hipchat:recipient.hipchat",
                                event: Notifications.NotificationEvent.DEPLOYMENT_FAILED,
                                apiToken: 'XXX',
                                room: 'MyRoom',
                                notify: true
                        )]),
                        permissions:  new Permissions(
                                user: ['diego': [PermissionTypes.PermissionType.VIEW]],
                                group: ['devops': [PermissionTypes.PermissionType.VIEW, PermissionTypes.PermissionType.DEPLOY]],
                                other: ['ROLE_USER': [PermissionTypes.PermissionType.VIEW, PermissionTypes.PermissionType.EDIT]]
                        )
                )],
                releaseVersioning: new ReleaseVersioning(
                        nextReleaseVersion: "1.0-\${bamboo.buildNumber}",
                        autoIncrement: false,
                        variables: ["test3", "test4"]
                )
        )
    }

    def 'deployment projects in external script'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/deployprojs/DeploymentProjectsInExternalScript.groovy').text))

        then:
        def deploymentProjects = results.projects[0].plans[0].deploymentProjects
        deploymentProjects.size() == 2
        deploymentProjects[0].name == 'local project 1'
        deploymentProjects[0].environments.size() == 2
        deploymentProjects[1].name == 'local project 2'
        deploymentProjects[1].environments.size() == 2
    }

}