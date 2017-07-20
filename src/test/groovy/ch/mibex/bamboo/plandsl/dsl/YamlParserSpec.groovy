package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.branches.*
import ch.mibex.bamboo.plandsl.dsl.dependencies.AdvancedDependencyOptions
import ch.mibex.bamboo.plandsl.dsl.dependencies.Dependencies
import ch.mibex.bamboo.plandsl.dsl.dependencies.Dependency
import ch.mibex.bamboo.plandsl.dsl.deployprojs.*
import ch.mibex.bamboo.plandsl.dsl.jobs.ArtifactDefinition
import ch.mibex.bamboo.plandsl.dsl.jobs.ArtifactDependency
import ch.mibex.bamboo.plandsl.dsl.jobs.Requirement
import ch.mibex.bamboo.plandsl.dsl.notifications.*
import ch.mibex.bamboo.plandsl.dsl.permissions.PermissionTypes
import ch.mibex.bamboo.plandsl.dsl.permissions.Permissions
import ch.mibex.bamboo.plandsl.dsl.plans.ExpirationDetails
import ch.mibex.bamboo.plandsl.dsl.plans.Miscellaneous
import ch.mibex.bamboo.plandsl.dsl.scm.*
import ch.mibex.bamboo.plandsl.dsl.scm.auth.DefaultMercurialAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SharedCredentialsAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SshWithoutPassphraseAuth
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedGitOptions
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedHgMercurialOptions
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedSvnOptions
import ch.mibex.bamboo.plandsl.dsl.scm.web.BitbucketWebRepository
import ch.mibex.bamboo.plandsl.dsl.scm.web.FisheyeWebRepository
import ch.mibex.bamboo.plandsl.dsl.scm.web.WebRepository
import ch.mibex.bamboo.plandsl.dsl.tasks.*
import ch.mibex.bamboo.plandsl.dsl.triggers.*
import ch.mibex.bamboo.plandsl.dsl.variables.Variable
import ch.mibex.bamboo.plandsl.dsl.variables.Variables
import spock.lang.Specification

class YamlParserSpec extends Specification {

    def 'empty YAML'() {
        setup:
        def yamlParser = new YamlParser(new NullBambooFacade())

        when:
        def project = yamlParser.parse('')

        then:
        project == null
    }

    def 'simple plan'() {
        setup:
        def yamlParser = new YamlParser(new NullBambooFacade())

        when:
        def project = yamlParser.parse(getClass().getResource('/yaml/SimplePlan.yml').text)

        then:
        project.key == 'MYPROJECT'
        project.name == 'This is my project'
        project.plans[0].key == 'MYPLAN'
        project.plans[0].name == 'my plan'
    }

    def 'full YAML'() {
        setup:
        def yamlParser = new YamlParser(new NullBambooFacade(
                ['mykey': 'myvalue',
                 'mykey2': 'myvalue2',
                 'mypw': 'topsecret',
                 'privateKey': 'privateKeyContent',
                 'apiKey': 'apiValue',
                 'twilioAccountSid': 'SID',
                 'twilioAuthToken': 'TOKEN',
                 'smsFromNumber': '012345678',
                 'smsToNumber': '0123456789',
                 'hipchat': 'HIPTOKEN',
                ]
        ))

        when:
        def project = yamlParser.parse(getClass().getResource('/yaml/Full.yml').text)

        then:
        project.key == 'MYPROJECT'
        project.name == 'This is my project'
        project.plans[0].key == 'MYPLAN'
        project.plans[0].name == 'my plan'
        project.plans[0].dependencies == new Dependencies(
                dependencies: [new Dependency(planKey: 'MYPLAN1'), new Dependency(planKey: 'MYPLAN2')],
                blockingStrategy: Dependencies.DependencyBlockingStrategy.BLOCK_BUILD_IF_PARENT_BUILDS_ARE_QUEUED,
                advancedOptions: new AdvancedDependencyOptions(
                        enableDependenciesForAllBranches: true,
                        triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully: true,
                        autoDependencyManagement: false
                )
        )
        project.plans[0].miscellaneous == new Miscellaneous(
                expireNothing: true,
                expirationDetails: new ExpirationDetails(
                        keepBuildsWithLabels: ['label1', 'label2', 'label3'],
                        expireAfter: 3,
                        expireTimeUnit: ExpirationDetails.TimeUnit.DAYS,
                        minimumBuildsToKeep: 10,
                        expireBuildResults: true,
                        expireBuildArtifacts: false,
                        expireBuildLogs: true
                ),
                customSettings: [
                        'custom.ruby-config-runtime' : 'SYSTEM 2.0.0-p648@default',
                        'custom.ruby-config-environmentVariables': 'SOME_VAR="-D123 -R345"'
                ]
        )
        project.plans[0].variables == new Variables(
                variables: [
                        new Variable('myPlanKey1', 'myPlanValue1'),
                        new Variable('myPlanKey2', 'myPlanValue2')
                ]
        )
        project.plans[0].stages[0].name == 'mystage'
        project.plans[0].stages[0].jobs[0].key == 'MYJOB'
        project.plans[0].stages[0].jobs[0].name == 'myjob'
        project.plans[0].stages[0].jobs[0].tasks.tasks[0] == new ScriptTask(
                inlineScript: new InlineScript(scriptBody: 'myvalue')
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[1] == new CommandTask(
                description: 'my command',
                enabled: true,
                isFinal: true,
                workingSubDirectory: 'mydir',
                argument: '-n',
                environmentVariables: 'what=EVER',
                executable: 'atlas-clean'
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[2] == new CustomTask(
                pluginKey: 'ch.mibex.bamboo.plandsl',
                taskConfig: ['key1': 'value1', 'key2': 'value2']
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[3] == new ArtifactDownloaderTask(
                artifacts: [new ArtifactDownloadConfiguration(
                        name: 'JAR',
                        destinationPath: 'out',
                        sourcePlanKey: 'myPlan'
                )]
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[4] == new CleanWorkingDirTask()
        project.plans[0].stages[0].jobs[0].tasks.tasks[5] == new DeployPluginTask(
                productType: DeployPluginTask.ProductType.JIRA,
                deployURL: 'http://myserver/jira',
                deployUsername: 'admin',
                deployPasswordVariable: 'topsecret',
                deployBranchEnabled: true,
                certificateCheckDisabled: false,
                useAtlassianIdWebSudo: true,
                deployArtifactName: 'jar'
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[6] == new DockerTask(
                repository: 'myrepo',
                command: DockerTask.DockerCommand.BUILD,
                existingDockerfile: true,
                dockerfileContents: 'FROM debian:jessie-slim',
                doNotUseCachingWhenBuildingImage: false,
                saveTheImageAsFile: 'image.iso',
                environmentVariables: 'what=EVER',
                workingSubDirectory: '.'
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[7] == new HerokuDeployWarTask(
                apiKey: 'apiValue',
                appName: 'myapp',
                warFile: 'my.war',
                enabled: false,
                description: 'push to heroku'
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[8] == new InjectBambooVariablesTask(
                propertiesFilePath: 'path/to/props.txt',
                namespace: 'myNs',
                variablesScope: InjectBambooVariablesTask.VariablesScope.RESULT
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[9] == new JUnitParserTask(
                testResultsDirectory: 'path/to/test/results',
                pickUpTestResultsCreatedOutsideOfBuild: true
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[10] == new Maven3Task(
                goal: 'install',
                executable: 'maven323',
                buildJdk: 'jdk8',
                environmentVariables: 'what=EVER',
                workingSubDirectory: '.',
                useMavenReturnCode: false,
                projectFile: 'a/b/pom.xml',
                withTests: new WithTests(
                        testResultsDirectory: 'tests/'
                )
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[11] == new MsBuildTask(
                executable: 'msbuild',
                projectFile: 'MySolution.sln',
                workingSubDirectory: '.',
                environmentVariables: 'what=EVER',
                options: '/d',
                description: 'run MSBuild'
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[12] == new NodeJsTask(
                executable: '/usr/bin/nodejs',
                script: 'package.json',
                environmentVariables: 'what=EVER',
                workingSubDirectory: '.',
                arguments: '-n'
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[13] == new NpmTask(
                executable: '/usr/bin/nodejs',
                command: 'install',
                environmentVariables: 'what=EVER',
                workingSubDirectory: '.',
                useIsolatedCache: true
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[14] == new ScpTask(
                host: 'localhost',
                userName: 'bob',
                description: 'what=EVER',
                authType: new PasswordAuth(
                     password: 'topsecret'
                ),
                localPath: '*.zip,*.jar',
                artifactLocalPath: new ScpArtifactByLocalPath(
                     useAntPatternsToSelectFiles: true,
                ),
                remotePath: 'a/b',
                advancedOptions: new SshAdvancedOptions(
                        hostFingerprint: 'test',
                        port: 22
                )
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[15] == new ShipItPluginTask(
                deployArtifactName: 'Plan DSL',
                description: 'ship it to the Atlassian Marketplace',
                enabled: true,
                isFinal: false,
                onlyAllowToTriggerFromJira: true,
                runOnBranchBuilds: false,
                publicVersion: true,
                deduceBuildNrFromPluginVersion: true,
                bambooUserId: 'admin',
                jqlToCollectReleaseNotes: 'status in (resolved,closed,done)'
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[16] == new SshTask(
            host: 'localhost',
            userName: 'bob',
            description: 'show dir on remote server',
            authType: new SshWithoutPassphraseAuth(
                    privateKey: 'privateKeyContent',
                    enableSshCompression: true
            ),
            command: 'ls -l',
            advancedOptions: new SshAdvancedOptions(
                hostFingerprint: 'test',
                port: 22
            )
        )
        project.plans[0].stages[0].jobs[0].tasks.tasks[17] == new VcsCheckoutTask(
                forceCleanBuild: true,
                repositories: [
                        new CheckoutRepository(name: 'myrepo1', checkoutDirectory: 'a/'),
                        new CheckoutRepository(name: 'myrepo2', checkoutDirectory: 'b/')
                ]
        )
        project.plans[0].stages[0].jobs[0].requirements.requirements[0] == new Requirement(
                capabilityKey: 'system.builder.ant.Ant',
                matchType: new Requirement.Equals(matchValue: '1')
        )
        project.plans[0].stages[0].jobs[0].requirements.requirements[1] == new Requirement(
                capabilityKey: 'system.builder.mvn.Maven3',
                matchType: new Requirement.Exists()
        )
        project.plans[0].stages[0].jobs[0].artifacts.artifactDependencies[0] == new ArtifactDependency(
                name: 'myExe',
                destinationDirectory: 'targets'
        )
        project.plans[0].stages[0].jobs[0].artifacts.artifactDefinitions[0] == new ArtifactDefinition(
                location: 'mydir',
                copyPattern: '*.exe',
                isShared: 'true',
                name:  'def'
        )
        project.plans[0].scm.scms[0] == new ScmBitbucketCloud(
                repoSlug: 'repo1',
                branch: 'master',
                scmType: new ScmBitbucketGit(),
                authType: new PasswordAuth(userName: 'user', password: 'pw')
        )
        project.plans[0].scm.scms[1] == new ScmBitbucketServer(
                serverName: 'MYBBS',
                repositoryUrl: 'http://localhost:7990/git',
                projectKey: 'PROJECT_1',
                repoSlug: 'myrepo',
                branch: 'develop',
                advancedOptions: new AdvancedGitOptions(
                        enableRepositoryCachingOnRemoteAgents: true,
                        useShallowClones: true,
                        useSubmodules: false,
                        commandTimeoutInMinutes: 15,
                        verboseLogs: true,
                        fetchWholeRepository: false,
                        enableLfsSupport: true,
                        quietPeriod: new QuietPeriod(
                                maximumRetries: 15,
                                waitTimeInSeconds: 10
                        ),
                        includeExcludeFiles: new IncludeExcludeFiles(
                                matchType: ScmType.MatchType.INCLUDE_ONLY_MATCHING_CHANGES,
                                filePattern: '*.exe'
                        ),
                        excludeChangesetsRegex: true,
                        webRepository: new WebRepository(
                                type: new FisheyeWebRepository(
                                        url: 'http://fisheye',
                                        repositoryPath: 'my/path',
                                        repositoryName: 'repo1'
                                )
                        )
                )
        )
        project.plans[0].scm.scms[2] == new ScmCustom(
                config: [
                        'stellarity.tfs.repository.url': 'http://localhost:8080/tfs/DefaultCollection',
                        'stellarity.tfs.repository.path': '$/test-prj/src',
                        'stellarity.tfs.repository.username': 'admin',
                        'stellarity.tfs.temporary.passwordChange': 'true',
                        'stellarity.tfs.temporary.password': 'CHANGEIT',
                        'stellarity.tfs.repository.removeworkspace': 'true',
                        'stellarity.tfs.repository.versionspec': '1.x',
                        'selectedWebRepositoryViewer': 'com.stellarity.bamboo.tfs-repository-plugin:tfsViewer',
                        'stellarity.tfs.repository.filter.option': 'INCLUDE',
                        'stellarity.tfs.repository.filter.pattern': 'checkout'
                ],
                name: 'TFS',
                pluginKey: 'com.stellarity.bamboo.tfs-repository-plugin:tfs'
        )
        project.plans[0].scm.scms[3] == new ScmCvs(
                cvsRoot: "http://localhost:7990/bitbucket/scm/project_1/java-maven-simple.cvs",
                quietPeriodInSeconds: 60,
                module: "test",
                name: "myCvsRepo",
                moduleVersion: ScmCvs.CvsModuleVersion.HEAD,
                authType: new PasswordAuth(password: "pw")
        )
        project.plans[0].scm.scms[4] == new ScmGit(
                name: "myGitRepo",
                url: "http://localhost:7990/bitbucket/scm/project_1/java-maven-simple.git",
                branch: "master",
                authType: new SharedCredentialsAuth(
                        sharedCredentialsType: SharedCredentialsAuth.SharedCredentialsType.USERNAMEPW,
                        name: 'sharedpw'
                )
        )
        project.plans[0].scm.scms[5] == new ScmGithub(
                name: "myGithubRepo",
                repoSlug: "test/HelloWorld",
                branch: "master",
                authType: new PasswordAuth(userName: "test", password: "pw")
        )
        project.plans[0].scm.scms[6] == new ScmLinkedRepository(name: "myGlobalRepo1")
        project.plans[0].scm.scms[7] == new ScmMercurial(
                name: "myHg",
                repositoryUrl: "http://hg.red-bean.com/repos/test",
                branch: "master",
                authType: new DefaultMercurialAuth(),
                advancedOptions: new AdvancedHgMercurialOptions(
                        enableCommitIsolation: true,
                        commandTimeoutInMinutes: 180,
                        verboseLogs: true,
                        disableRepositoryCaching: false,
                        quietPeriod: new QuietPeriod(
                                waitTimeInSeconds: 120,
                                maximumRetries: 3
                        ),
                        includeExcludeFiles: new IncludeExcludeFiles(
                                matchType: ScmType.MatchType.EXCLUDE_ALL_MATCHING_CHANGES,
                                filePattern: 'exe'
                        ),
                        excludeChangesetsRegex: 'FIXES .*',
                        webRepository: new WebRepository(type: new BitbucketWebRepository())
                )
        )
        project.plans[0].scm.scms[8] == new ScmPerforce(
                name: "myPerforceRepo",
                port: "9091",
                client: "perforce",
                depotView: "//perforce/workspace",
                environmentVariables: "P4CHARSET=utf8",
                letBambooManageWorkspace: true,
                useClientMapping: true,
                passwordAuth: new PasswordAuth(userName: "admin", password: "pw")
        )
        project.plans[0].scm.scms[9] == new ScmSubversion(
                advancedOptions: new AdvancedSvnOptions(
                        detectChangesInExternals: true,
                        useSvnExport: true,
                        enableCommitIsolation: true,
                        autoDetectRootUrlForBranches: false,
                        branchesRootUrl: "/branches",
                        autoDetectRootUrlForTags: false,
                        tagRootUrl: "/tags",
                        quietPeriod: new QuietPeriod(
                                waitTimeInSeconds: 120,
                                maximumRetries: 3
                        ),
                        includeExcludeFiles: new IncludeExcludeFiles(
                                matchType: ScmType.MatchType.EXCLUDE_ALL_MATCHING_CHANGES,
                                filePattern: 'exe'
                        ),
                        excludeChangesetsRegex: 'FIXES .*',
                        webRepository: new WebRepository(
                                type: new FisheyeWebRepository(
                                        url: "http://localhost:7990",
                                        repositoryPath: "a/b/c",
                                        repositoryName: "d"
                                )
                        )
                ),
                repositoryUrl: "http://svn.red-bean.com/repos/test",
                userName: "admin",
                name: "mySvn",
                authType: new PasswordAuth(userName: "admin", password: "pw")
        )
        project.plans[0].notifications.notifications[0] == new EmailNotification(
                notificationRecipientType: EmailNotification.NOTIFICATION_RECIPIENT_TYPE,
                event: Notifications.NotificationEvent.ALL_BUILDS_COMPLETED,
                address: 'test@test.com'
        )
        project.plans[0].notifications.notifications[1] == new CommittersNotification(
                event: Notifications.NotificationEvent.CHANGE_OF_BUILD_STATUS
        )
        project.plans[0].notifications.notifications[2] == new CustomNotification(
                notificationRecipientType: 'ch.mibex.bamboo.smsnotification:smsnotification.recipient',
                event: Notifications.NotificationEvent.FAILED_BUILDS_AND_FIRST_SUCCESSFUL,
                numberOfFailures: 1,
                config: [
                        'twilioAccountSid': ['SID'],
                        'twilioAuthToken': ['TOKEN'],
                        'smsFromNumber': ['012345678'],
                        'smsToNumber': ['0123456789']
                ]
        )
        project.plans[0].notifications.notifications[3] == new HipChatNotification(
                apiToken: 'HIPTOKEN',
                event: Notifications.NotificationEvent.AFTER_X_BUILD_FAILURES,
                room: 'MYROOM',
                notify: true
        )
        project.plans[0].notifications.notifications[4] == new GroupNotification(
                event: Notifications.NotificationEvent.COMMENT_ADDED,
                group: 'mygroup'
        )
        project.plans[0].notifications.notifications[5] == new ImAddressNotification(
                event: Notifications.NotificationEvent.CHANGE_OF_RESPONSIBILITIES,
                instantMessagingAddress: 'MyAddress'
        )
        project.plans[0].notifications.notifications[6] == new ResponsibleUsersNotification(
                event: Notifications.NotificationEvent.ALL_JOBS_COMPLETED
        )
        project.plans[0].notifications.notifications[7] == new StashLegacyNotification(
                event: Notifications.NotificationEvent.CHANGE_OF_JOB_STATUS
        )
        project.plans[0].notifications.notifications[8] == new UserNotification(
                event: Notifications.NotificationEvent.FAILED_JOBS_AND_FIRST_SUCCESSFUL,
                user: 'bob'
        )
        project.plans[0].notifications.notifications[9] == new WatchersNotification(
                event: Notifications.NotificationEvent.FIRST_FAILED_JOB_FOR_PLAN
        )
        project.plans[0].triggers.triggers[0] == new BitbucketServerTrigger()
        project.plans[0].triggers.triggers[1] == new ManualTrigger()
        project.plans[0].triggers.triggers[2] == new PollingTrigger(
                periodicTrigger: new PeriodicTrigger(
                        pollingFrequencyInSecs: 30
                )
        )
        project.plans[0].triggers.triggers[3] == new ScheduledTrigger(
                cronExpression: '* * * * * * *',
                onlyRunIfOtherPlansArePassing: new OnlyIfOthersPassingTriggerCondition(
                        planKeys: ['SEED-SEED-JOB1']
                )
        )
        project.plans[0].triggers.triggers[4] == new OnceADayTrigger(
                buildTime: '17:30'
        )
        project.plans[0].triggers.triggers[5] == new RemoteTrigger(
                repositories: ['repo1', 'repo2'],
                ipAddresses: ['192.168.0.1', '192.168.0.2'],
        )
        project.plans[0].branches.branches[0] == new Branch(
                name: 'feature_1234',
                vcsBranchName: 'feature/1234',
                cleanupAutomatically: true,
                description: 'my important feature branch',
                triggers: new Triggers(triggers: [new BitbucketServerTrigger(description: 'run when push')]),
                notifications: Branch.NotifyOnNewBranchesType.USE_PLANS_NOTIFICATION_SETTINGS,
                merging: new BranchMerging(
                    mergeStrategy: new BranchUpdater(planBranchKey: 'DEVELOP', pushEnabled: false)
                ),
                variables: new Variables(
                    variables: [new Variable('name', 'value')]
                )
        )
        project.plans[0].branches.autoBranchManagement == new AutoBranchManagement(
                deletePlanBranchesAfterDays: 7,
                deletedBranchesStrategy: AutoBranchManagement.DeletedBranchesStrategy.DELETE_PLAN_BRANCHES_AFTER_DAYS,
                inactiveBranchesStrategy: AutoBranchManagement.InactiveBranchesStrategy.DELETE_INACTIVE_PLAN_BRANCHES_AFTER_DAYS,
                deleteInactivePlanBranchesAfterDays: 14,
                newBranchesStrategy: AutoBranchManagement.NewBranchesStrategy.NEW_PLAN_BRANCHES_FOR_PULL_REQUESTS
        )
        project.plans[0].branches.notifications == Branch.NotifyOnNewBranchesType.NOTIFY_COMMITTERS_FOR_FAVOURITED_BRANCHES
        project.plans[0].branches.triggers == Branches.NewPlanBranchesTriggerType.SAME_AS_IN_PARENT_PLAN
        project.plans[0].deploymentProjects[0] == new DeploymentProject(
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
                          'bob': [PermissionTypes.PermissionType.VIEW, PermissionTypes.PermissionType.EDIT],
                          'carol': [PermissionTypes.PermissionType.EDIT]
                  ],
                  group: [
                          'devops': [PermissionTypes.PermissionType.ADMIN, PermissionTypes.PermissionType.VIEW]
                  ],
                  other: [
                          (Permissions.OtherUserType.LOGGED_IN_USERS): [PermissionTypes.PermissionType.VIEW, PermissionTypes.PermissionType.EDIT]
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
                                           inlineScript: new InlineScript(
                                                   interpreter: ScriptTask.ScriptInterpreter.LEGACY_SH_BAT,
                                                   scriptBody: 'myvalue2'
                                           )
                                   )]
                            ),
                            variables: new Variables(
                                    variables: [
                                            new Variable('myKey1', 'myValue1'),
                                            new Variable('myKey2', 'myValue2')
                                    ]
                            ),
                            triggers: [
                                    triggers: [
                                            new AfterSuccessfulBuildDeploymentTrigger(
                                                    customPlanBranchName: 'DEV',
                                                    description: 'run after successful build',
                                                    enabled: true
                                            ),
                                            new AfterSuccessfulDeploymentTrigger(
                                                    triggeringEnvironment: 'UAT'
                                            ),
                                            new AfterSuccessfulStageDeploymentTrigger(
                                                    planStageToTriggerThisDeployment: 'STAGE1',
                                                    customPlanBranchName: 'TEST',
                                                    enabled: true
                                            ),
                                            new ScheduledDeploymentTrigger(
                                                customPlanBranchName: 'PROD',
                                                cronExpression: '0 0 0 6 * *'
                                            )
                                    ]
                            ],
                            notifications: [
                                    notifications: [
                                            new HipChatNotification(
                                                    apiToken: 'ABC',
                                                    event: Notifications.NotificationEvent.DEPLOYMENT_FAILED,
                                                    notify: true,
                                                    room: 'myroom'
                                            ),
                                            new ResponsibleUsersNotification(),
                                            new StashLegacyNotification(),
                                            new WatchersNotification()
                                    ]
                            ],
                            permissions: new Permissions(
                                    user: [
                                            'paul': [PermissionTypes.PermissionType.VIEW, PermissionTypes.PermissionType.EDIT],
                                            'peter': [PermissionTypes.PermissionType.ADMIN, PermissionTypes.PermissionType.VIEW]
                                    ],
                                    group: [
                                            'devops': [PermissionTypes.PermissionType.VIEW, PermissionTypes.PermissionType.EDIT]
                                    ],
                                    other: [
                                            (Permissions.OtherUserType.ANONYMOUS_USERS): [PermissionTypes.PermissionType.VIEW, PermissionTypes.PermissionType.EDIT]
                                    ]
                            ),
                        )
                ]
        )
    }

//    def 'multiple projects'() {
//        setup:
//        def yamlParser = new YamlParser(new NullBambooFacade())
//
//        when:
//        def projects = yamlParser.parse(getClass().getResource('/yaml/MultipleProjects.yml').text)
//
//        then:
//        project.key == 'MYPROJECT1'
//        project.name == 'This is my project 1'
//        projects[1].key == 'MYPROJECT2'
//        projects[1].name == 'This is my project 2'
//    }
}