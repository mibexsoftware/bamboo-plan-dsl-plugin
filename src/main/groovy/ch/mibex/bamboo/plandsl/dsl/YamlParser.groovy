package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.branches.AutoBranchManagement
import ch.mibex.bamboo.plandsl.dsl.branches.GateKeeper
import ch.mibex.bamboo.plandsl.dsl.notifications.EmailNotification
import ch.mibex.bamboo.plandsl.dsl.notifications.HipChatNotification
import ch.mibex.bamboo.plandsl.dsl.notifications.Notifications
import ch.mibex.bamboo.plandsl.dsl.permissions.PermissionTypes
import ch.mibex.bamboo.plandsl.dsl.permissions.Permissions
import ch.mibex.bamboo.plandsl.dsl.scm.ScmBitbucketCloud
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import ch.mibex.bamboo.plandsl.dsl.tasks.CustomTask
import ch.mibex.bamboo.plandsl.dsl.tasks.ScriptTask
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
        constructor.addTypeDescription(new TypeDescription(ScheduledTrigger, '!scheduled'))
        constructor.addTypeDescription(new TypeDescription(GateKeeper, '!gateKeeper'))
        constructor.addTypeDescription(new TypeDescription(ScriptTask, '!script'))
        constructor.addTypeDescription(new TypeDescription(CustomTask, '!custom'))
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
                    def variables = env.variables ?: []
                    if (variables) {
                        def newVariables = [:]
                        newVariables.variables = variables
                        env.variables = newVariables
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
                }
            }
        }
        yaml.dump(map)
    }

}
