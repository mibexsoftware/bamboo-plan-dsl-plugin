package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.branches.*
import ch.mibex.bamboo.plandsl.dsl.dependencies.Dependencies
import ch.mibex.bamboo.plandsl.dsl.deployprojs.AfterSuccessfulBuildDeploymentTrigger
import ch.mibex.bamboo.plandsl.dsl.deployprojs.AfterSuccessfulDeploymentTrigger
import ch.mibex.bamboo.plandsl.dsl.deployprojs.AfterSuccessfulStageDeploymentTrigger
import ch.mibex.bamboo.plandsl.dsl.deployprojs.ScheduledDeploymentTrigger
import ch.mibex.bamboo.plandsl.dsl.jobs.CloverCodeCoverage
import ch.mibex.bamboo.plandsl.dsl.jobs.Requirement
import ch.mibex.bamboo.plandsl.dsl.notifications.*
import ch.mibex.bamboo.plandsl.dsl.permissions.PermissionTypes
import ch.mibex.bamboo.plandsl.dsl.permissions.Permissions
import ch.mibex.bamboo.plandsl.dsl.plans.ExpirationDetails
import ch.mibex.bamboo.plandsl.dsl.scm.*
import ch.mibex.bamboo.plandsl.dsl.scm.auth.*
import ch.mibex.bamboo.plandsl.dsl.scm.web.BitbucketWebRepository
import ch.mibex.bamboo.plandsl.dsl.scm.web.FisheyeWebRepository
import ch.mibex.bamboo.plandsl.dsl.scm.web.MercurialWebRepository
import ch.mibex.bamboo.plandsl.dsl.scm.web.StashWebRepository
import ch.mibex.bamboo.plandsl.dsl.tasks.*
import ch.mibex.bamboo.plandsl.dsl.triggers.*
import org.yaml.snakeyaml.TypeDescription
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.AbstractConstruct
import org.yaml.snakeyaml.constructor.Constructor
import org.yaml.snakeyaml.introspector.BeanAccess
import org.yaml.snakeyaml.nodes.ScalarNode
import org.yaml.snakeyaml.nodes.Tag

class YamlParser {
    private BambooFacade bambooFacade

    YamlParser(BambooFacade bambooFacade) {
        this.bambooFacade = bambooFacade
    }

    Project parse(File file) {
        parse(file.text)
    }

    Project parse(String input) {
        Constructor constructor = new EnvConstructor(bambooFacade)
        addTypeAliases(constructor)
        def yaml = new Yaml(constructor)
        yaml.setBeanAccess(BeanAccess.FIELD)
        String str = removeDuplicateNesting(yaml, input)
        yaml.loadAs(str, YamlDsl).project
    }

    private void addTypeAliases(Constructor constructor) {
        addTriggerTypeAliases(constructor)
        addTaskTypeAliases(constructor)
        addNotificationTypeAliases(constructor)
        addScmTypeAliases(constructor)
        addScmTypeAliases(constructor)
        addAutoBranchTypeAliases(constructor)
        addOtherTypeAliases(constructor)
    }

    private void addOtherTypeAliases(Constructor constructor) {
        constructor.addTypeDescription(new TypeDescription(GateKeeper, '!gateKeeper'))
        constructor.addTypeDescription(new TypeDescription(BranchUpdater, '!branchUpdater'))
        constructor.addTypeDescription(new TypeDescription(PermissionTypes.PermissionType, '!permission'))
        constructor.addTypeDescription(new TypeDescription(Permissions.OtherUserType, '!userType'))
        constructor.addTypeDescription(
                new TypeDescription(Dependencies.DependencyBlockingStrategy, '!dependencyBlocking')
        )
        constructor.addTypeDescription(new TypeDescription(CloverCodeCoverage.IntegrationOptions, '!integration'))
        constructor.addTypeDescription(new TypeDescription(ExpirationDetails.TimeUnit, '!timeUnit'))
        constructor.addTypeDescription(
                new TypeDescription(Branch.NotifyOnNewBranchesType, '!notifyOnNewBranchesType')
        )
        constructor.addTypeDescription(
                new TypeDescription(Branches.NewPlanBranchesTriggerType, '!newPlanBranchesTriggerType')
        )
        constructor.addTypeDescription(new TypeDescription(Requirement.Equals, '!equals'))
        constructor.addTypeDescription(new TypeDescription(Requirement.Exists, '!exists'))
        constructor.addTypeDescription(new TypeDescription(Requirement.Matches, '!matches'))
    }

    private void addAutoBranchTypeAliases(Constructor constructor) {
        constructor.addTypeDescription(
                new TypeDescription(AutoBranchManagement.DeletedBranchesStrategy, '!deletedBranches')
        )
        constructor.addTypeDescription(
                new TypeDescription(AutoBranchManagement.InactiveBranchesStrategy, '!inactiveBranches')
        )
        constructor.addTypeDescription(
                new TypeDescription(AutoBranchManagement.NewBranchesStrategy, '!newBranches')
        )
    }

    private void addScmTypeAliases(Constructor constructor) {
        constructor.addTypeDescription(new TypeDescription(ScmBitbucketCloud, '!bitbucketCloud'))
        constructor.addTypeDescription(new TypeDescription(ScmBitbucketServer, '!bitbucketServer'))
        constructor.addTypeDescription(new TypeDescription(FisheyeWebRepository, '!fisheyeWeb'))
        constructor.addTypeDescription(new TypeDescription(BitbucketWebRepository, '!bitbucketWeb'))
        constructor.addTypeDescription(new TypeDescription(MercurialWebRepository, '!mercurialWeb'))
        constructor.addTypeDescription(new TypeDescription(StashWebRepository, '!stashWeb'))
        constructor.addTypeDescription(new TypeDescription(ScmType.MatchType, '!matchType'))
        constructor.addTypeDescription(new TypeDescription(ScmCustom, '!customScm'))
        constructor.addTypeDescription(new TypeDescription(ScmCvs, '!cvs'))
        constructor.addTypeDescription(new TypeDescription(ScmCvs.CvsModuleVersion, '!cvsModuleVersion'))
        constructor.addTypeDescription(new TypeDescription(ScmGit, '!git'))
        constructor.addTypeDescription(new TypeDescription(SharedCredentialsAuth, '!sharedCredentials'))
        constructor.addTypeDescription(
                new TypeDescription(SharedCredentialsAuth.SharedCredentialsType, '!sharedCredentialsType')
        )
        constructor.addTypeDescription(new TypeDescription(ScmGithub, '!github'))
        constructor.addTypeDescription(new TypeDescription(ScmLinkedRepository, '!linkedRepository'))
        constructor.addTypeDescription(new TypeDescription(ScmMercurial, '!mercurial'))
        constructor.addTypeDescription(new TypeDescription(DefaultMercurialAuth, '!defaultMercurialAuth'))
        constructor.addTypeDescription(new TypeDescription(ScmPerforce, '!perforce'))
        constructor.addTypeDescription(new TypeDescription(ScmSubversion, '!subversion'))
        constructor.addTypeDescription(new TypeDescription(PasswordAuth, '!password'))
    }

    private void addNotificationTypeAliases(Constructor constructor) {
        constructor.addTypeDescription(new TypeDescription(CommittersNotification, '!committers'))
        constructor.addTypeDescription(new TypeDescription(CustomNotification, '!customNotification'))
        constructor.addTypeDescription(new TypeDescription(EmailNotification, '!email'))
        constructor.addTypeDescription(new TypeDescription(HipChatNotification, '!hipchat'))
        constructor.addTypeDescription(new TypeDescription(Notifications.NotificationEvent, '!event'))
        constructor.addTypeDescription(new TypeDescription(GroupNotification, '!group'))
        constructor.addTypeDescription(new TypeDescription(ImAddressNotification, '!imAddress'))
        constructor.addTypeDescription(new TypeDescription(ResponsibleUsersNotification, '!responsibleUsers'))
        constructor.addTypeDescription(new TypeDescription(StashLegacyNotification, '!stashLegacy'))
        constructor.addTypeDescription(new TypeDescription(UserNotification, '!user'))
        constructor.addTypeDescription(new TypeDescription(WatchersNotification, '!watchers'))
    }

    private void addTriggerTypeAliases(Constructor constructor) {
        constructor.addTypeDescription(new TypeDescription(BitbucketServerTrigger, '!bitbucketServerTrigger'))
        constructor.addTypeDescription(
                new TypeDescription(AfterSuccessfulBuildDeploymentTrigger, '!afterSuccessfulBuildDeployment')
        )
        constructor.addTypeDescription(
                new TypeDescription(AfterSuccessfulDeploymentTrigger, '!afterSuccessfulDeployment')
        )
        constructor.addTypeDescription(
                new TypeDescription(AfterSuccessfulStageDeploymentTrigger, '!afterSuccessfulStageDeployment')
        )
        constructor.addTypeDescription(new TypeDescription(ScheduledDeploymentTrigger, '!scheduledDeployment'))
        constructor.addTypeDescription(new TypeDescription(ScheduledTrigger, '!scheduled'))
        constructor.addTypeDescription(new TypeDescription(ManualTrigger, '!manual'))
        constructor.addTypeDescription(new TypeDescription(OnceADayTrigger, '!onceADay'))
        constructor.addTypeDescription(new TypeDescription(PollingTrigger, '!polling'))
        constructor.addTypeDescription(new TypeDescription(RemoteTrigger, '!remote'))
    }

    private void addTaskTypeAliases(Constructor constructor) {
        constructor.addTypeDescription(new TypeDescription(ArtifactDownloaderTask, '!artifactDownload'))
        constructor.addTypeDescription(new TypeDescription(CleanWorkingDirTask, '!cleanWorkingDir'))
        constructor.addTypeDescription(new TypeDescription(CommandTask, '!command'))
        constructor.addTypeDescription(new TypeDescription(ScriptTask, '!script'))
        constructor.addTypeDescription(new TypeDescription(ScriptTask.ScriptInterpreter, '!scriptInterpreter'))
        constructor.addTypeDescription(new TypeDescription(CustomTask, '!customTask'))
        constructor.addTypeDescription(new TypeDescription(DeployPluginTask.ProductType, '!productType'))
        constructor.addTypeDescription(new TypeDescription(DeployPluginTask, '!deployPlugin'))
        constructor.addTypeDescription(new TypeDescription(DockerTask, '!docker'))
        constructor.addTypeDescription(new TypeDescription(DockerTask.DockerCommand, '!dockerCommand'))
        constructor.addTypeDescription(new TypeDescription(HerokuDeployWarTask, '!herokuDeployWar'))
        constructor.addTypeDescription(new TypeDescription(InjectBambooVariablesTask, '!injectBambooVariables'))
        constructor.addTypeDescription(
                new TypeDescription(InjectBambooVariablesTask.VariablesScope, '!variablesScope')
        )
        constructor.addTypeDescription(new TypeDescription(JUnitParserTask, '!junitParser'))
        constructor.addTypeDescription(new TypeDescription(Maven3Task, '!maven3x'))
        constructor.addTypeDescription(new TypeDescription(MsBuildTask, '!msbuild'))
        constructor.addTypeDescription(new TypeDescription(NodeJsTask, '!nodeJs'))
        constructor.addTypeDescription(new TypeDescription(NpmTask, '!npm'))
        constructor.addTypeDescription(new TypeDescription(ScpTask, '!scp'))
        constructor.addTypeDescription(new TypeDescription(PasswordAuth, '!passwordAuth'))
        constructor.addTypeDescription(new TypeDescription(ScriptTask, '!script'))
        constructor.addTypeDescription(new TypeDescription(ShipItPluginTask, '!shipit2marketplace'))
        constructor.addTypeDescription(new TypeDescription(SshTask, '!ssh'))
        constructor.addTypeDescription(new TypeDescription(SshWithoutPassphraseAuth, '!sshWithoutPassphraseAuth'))
        constructor.addTypeDescription(new TypeDescription(SslClientCertificateAuth, '!sslClientCertificateAuth'))
        constructor.addTypeDescription(new TypeDescription(DefaultMercurialAuth, '!defaultMercurialAuth'))
        constructor.addTypeDescription(new TypeDescription(SshAuth, 'sSshAuth'))
        constructor.addTypeDescription(new TypeDescription(SharedCredentialsAuth, 'sharedCredentialsAuth'))
        constructor.addTypeDescription(new TypeDescription(VcsCheckoutTask, '!checkout'))
    }

    private String removeDuplicateNesting(Yaml yaml, String input) {
        def map = yaml.loadAs(input, Map) ?: [:]
        def project = map.project ?: [:]
        def plans = project.plans ?: []
        plans.each {
            def plan = it as Map

            def scm = plan.scm ?: []
            if (scm) {
                def newScm = [:]
                newScm.scms =  scm
                plan.scm = newScm
            }

            def notifications = plan.notifications
            if (notifications) {
                def newNotifications = [:]
                newNotifications.notifications = notifications
                plan.notifications = newNotifications
            }

            def variables = plan.variables
            if (variables) {
                def newVariables = [:]
                newVariables.variables = variables
                plan.variables = newVariables
            }

            def triggers = plan.triggers
            if (triggers) {
                def newTriggers = [:]
                newTriggers.triggers = triggers
                plan.triggers = newTriggers
            }

            def branches = plan.branches ? plan.branches.branches : []
            branches.each {
                def branch = it as Map
                if (branch.triggers) {
                    def newTriggers = [:]
                    newTriggers.triggers = branch.triggers
                    branch.triggers = newTriggers
                }
                if (branch.variables) {
                    def newVariables = [:]
                    newVariables.variables = branch.variables
                    branch.variables = newVariables
                }
            }

            plan.deploymentProjects.each {
                def deploymentProject = it as Map
                def environments = deploymentProject.environments ?: []
                environments.each {
                    def env = it as Map
                    def tasks = env.tasks ?: []
                    if (tasks) {
                        def newTasks = [:]
                        newTasks.tasks = tasks
                        env.tasks = newTasks
                    }
                    def envVariables = env.variables ?: []
                    if (envVariables) {
                        def newVariables = [:]
                        newVariables.variables = envVariables
                        env.variables = newVariables
                    }
                    def envTriggers = env.triggers ?: []
                    if (envTriggers) {
                        def newTriggers = [:]
                        newTriggers.triggers = envTriggers
                        env.triggers = newTriggers
                    }
                    def envNotifications = env.notifications ?: []
                    if (envNotifications) {
                        def newNotifications = [:]
                        newNotifications.notifications = envNotifications
                        env.notifications = newNotifications
                    }
                    if (env.agentsAssignment) {
                        def requirements = env.agentsAssignment.requirements ?: []
                        if (requirements) {
                            def newRequirements = [:]
                            newRequirements.requirements = requirements
                            env.agentsAssignment.requirements = newRequirements
                        }
                    }
                }
            }

            def stages = plan.stages ?: []
            stages.each {
                def stage = it as Map
                def jobs = stage.jobs ?: []
                jobs.each {
                    def job = it as Map
                    def tasks = job.tasks ?: []
                    if (tasks) {
                        def newTasks = [:]
                        newTasks.tasks = tasks
                        job.tasks = newTasks
                    }
                    def newArtifacts = [:]
                    def artifactDependencies = job.artifactDependencies ?: []
                    if (artifactDependencies) {
                        newArtifacts.artifactDependencies = artifactDependencies
                        job.remove('artifactDependencies')
                    }
                    def artifactDefinitions = job.artifactDefinitions ?: []
                    if (artifactDefinitions) {
                        newArtifacts.artifactDefinitions = artifactDefinitions
                        job.remove('artifactDefinitions')
                    }
                    job.artifacts = newArtifacts

                    def requirements = job.requirements ?: []
                    if (requirements) {
                        def newRequirements = [:]
                        newRequirements.requirements = requirements
                        job.requirements = newRequirements
                    }
                }
            }
        }
        yaml.dump(map)
    }

    static class YamlDsl {
        Project project
    }

    static class EnvConstructor extends Constructor {
        private final BambooFacade bambooFacade

        EnvConstructor(BambooFacade bambooFacade) {
            this.bambooFacade = bambooFacade
            this.yamlConstructors.put(new Tag('!env'), new ConstructEnv())
            this.yamlConstructors.put(new Tag('!encrypt'), new ConstructEncrypt())
        }

        private class ConstructEnv extends AbstractConstruct {

            @Override
            Object construct(org.yaml.snakeyaml.nodes.Node node) {
                if (!(node instanceof ScalarNode)) {
                    throw new IllegalArgumentException('Non-scalar !env: ' + node.toString())
                }
                String key = (String) constructScalar(node)
                bambooFacade.variableContext[key]
            }
        }

        private class ConstructEncrypt extends AbstractConstruct {

            @Override
            Object construct(org.yaml.snakeyaml.nodes.Node node) {
                if (!(node instanceof ScalarNode)) {
                    throw new IllegalArgumentException('Non-scalar !encrypt: ' + node.toString())
                }
                String text = (String) constructScalar(node)
                bambooFacade.encrypt(text)
            }
        }
    }

}
