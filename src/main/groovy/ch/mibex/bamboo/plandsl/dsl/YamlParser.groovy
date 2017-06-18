package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.branches.AutoBranchManagement
import ch.mibex.bamboo.plandsl.dsl.branches.GateKeeper
import ch.mibex.bamboo.plandsl.dsl.dependencies.Dependencies
import ch.mibex.bamboo.plandsl.dsl.deployprojs.AfterSuccessfulBuildDeploymentTrigger
import ch.mibex.bamboo.plandsl.dsl.jobs.CloverCodeCoverage
import ch.mibex.bamboo.plandsl.dsl.notifications.EmailNotification
import ch.mibex.bamboo.plandsl.dsl.notifications.HipChatNotification
import ch.mibex.bamboo.plandsl.dsl.notifications.Notifications
import ch.mibex.bamboo.plandsl.dsl.permissions.PermissionTypes
import ch.mibex.bamboo.plandsl.dsl.permissions.Permissions
import ch.mibex.bamboo.plandsl.dsl.plans.ExpirationDetails
import ch.mibex.bamboo.plandsl.dsl.scm.ScmBitbucketCloud
import ch.mibex.bamboo.plandsl.dsl.scm.auth.*
import ch.mibex.bamboo.plandsl.dsl.tasks.*
import ch.mibex.bamboo.plandsl.dsl.triggers.BitbucketServerTrigger
import ch.mibex.bamboo.plandsl.dsl.triggers.ScheduledTrigger
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

    static class YamlDsl {
        Project project
    }

    static class EnvConstructor extends Constructor {
        private final BambooFacade bambooFacade

        EnvConstructor(BambooFacade bambooFacade) {
            this.bambooFacade = bambooFacade
            this.yamlConstructors.put(new Tag('!env'), new ConstructEnv())
        }

        private class ConstructEnv extends AbstractConstruct {

            @Override
            Object construct(org.yaml.snakeyaml.nodes.Node node) {
                if (!(node instanceof ScalarNode)) {
                    throw new IllegalArgumentException('Non-scalar !env: ' + node.toString());
                }
                String key = (String) constructScalar(node)
                bambooFacade.variableContext[key]
            }
        }
    }

    YamlDsl parseYaml(String input) {
        Constructor constructor = new EnvConstructor(bambooFacade)
        addTypeAliases(constructor)
        def yaml = new Yaml(constructor)
        yaml.setBeanAccess(BeanAccess.FIELD)
        String str = removeDuplicateNesting(yaml, input)
        yaml.loadAs(str, YamlDsl)
    }

    private void addTypeAliases(Constructor constructor) {
        constructor.addTypeDescription(new TypeDescription(BitbucketServerTrigger, '!bitbucketServerTrigger'))
        constructor.addTypeDescription(new TypeDescription(AfterSuccessfulBuildDeploymentTrigger, '!afterSuccessfulBuildDeploymentTrigger'))
        constructor.addTypeDescription(new TypeDescription(ScheduledTrigger, '!scheduled'))

        constructor.addTypeDescription(new TypeDescription(GateKeeper, '!gateKeeper'))

        addTaskTypeAliases(constructor)

        constructor.addTypeDescription(new TypeDescription(ScmBitbucketCloud, '!bitbucket'))
        constructor.addTypeDescription(new TypeDescription(PasswordAuth, '!password'))

        constructor.addTypeDescription(new TypeDescription(EmailNotification, '!email'))
        constructor.addTypeDescription(new TypeDescription(HipChatNotification, '!hipchat'))
        constructor.addTypeDescription(new TypeDescription(Notifications.NotificationEvent, '!event'))

        constructor.addTypeDescription(new TypeDescription(AutoBranchManagement.DeletedBranchesStrategy, '!deletedBranches'))
        constructor.addTypeDescription(new TypeDescription(AutoBranchManagement.InactiveBranchesStrategy, '!inactiveBranches'))
        constructor.addTypeDescription(new TypeDescription(AutoBranchManagement.NewBranchesStrategy, '!newBranches'))

        constructor.addTypeDescription(new TypeDescription(PermissionTypes.PermissionType, '!permission'))
        constructor.addTypeDescription(new TypeDescription(Permissions.OtherUserType, '!userType'))
        constructor.addTypeDescription(new TypeDescription(Dependencies.DependencyBlockingStrategy, '!dependencyBlocking'))
        constructor.addTypeDescription(new TypeDescription(CloverCodeCoverage.IntegrationOptions, '!integration'))
        constructor.addTypeDescription(new TypeDescription(ExpirationDetails.TimeUnit, '!timeUnit'))
    }

    private void addTaskTypeAliases(Constructor constructor) {
        constructor.addTypeDescription(new TypeDescription(ArtifactDownloaderTask, '!artifactDownload'))
        constructor.addTypeDescription(new TypeDescription(CleanWorkingDirTask, '!cleanWorkingDir'))
        constructor.addTypeDescription(new TypeDescription(CommandTask, '!command'))
        constructor.addTypeDescription(new TypeDescription(ScriptTask, '!script'))
        constructor.addTypeDescription(new TypeDescription(CustomTask, '!custom'))
        constructor.addTypeDescription(new TypeDescription(DeployPluginTask.ProductType, '!productType'))
        constructor.addTypeDescription(new TypeDescription(DeployPluginTask, '!deployPlugin'))
        constructor.addTypeDescription(new TypeDescription(DockerTask, '!docker'))
        constructor.addTypeDescription(new TypeDescription(DockerTask.DockerCommand, '!dockerCommand'))
        constructor.addTypeDescription(new TypeDescription(HerokuDeployWarTask, '!herokuDeployWar'))
        constructor.addTypeDescription(new TypeDescription(InjectBambooVariablesTask, '!injectBambooVariables'))
        constructor.addTypeDescription(new TypeDescription(InjectBambooVariablesTask.VariablesScope, '!variablesScope'))
        constructor.addTypeDescription(new TypeDescription(JUnitParserTask, '!junitParser'))
        constructor.addTypeDescription(new TypeDescription(Maven3Task, '!maven3'))
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
        constructor.addTypeDescription(new TypeDescription(VcsCheckoutTask, '!vcsCheckout'))
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
                }
            }
        }
        yaml.dump(map)
    }

}
