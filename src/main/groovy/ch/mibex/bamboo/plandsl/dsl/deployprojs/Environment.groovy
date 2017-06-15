package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import ch.mibex.bamboo.plandsl.dsl.notifications.EnvironmentNotifications
import ch.mibex.bamboo.plandsl.dsl.permissions.Permissions
import ch.mibex.bamboo.plandsl.dsl.tasks.Tasks
import ch.mibex.bamboo.plandsl.dsl.variables.Variables
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * Environments represent where releases are deployed to.
 *
 * @since 1.1.0
 */
@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Environment extends BambooObject {
    private String name
    private Long id
    private String description
    private Tasks tasks = new Tasks(bambooFacade)
    private DeploymentTriggers triggers = new DeploymentTriggers(bambooFacade)
    private Variables variables = new Variables(bambooFacade)
    private EnvironmentNotifications notifications = new EnvironmentNotifications(bambooFacade)
    private Permissions permissions = new Permissions(bambooFacade)

    Environment(String name, BambooFacade bambooFacade) {
        this(name, null, bambooFacade)
    }

    Environment(String name, Long id, BambooFacade bambooFacade) {
        super(bambooFacade)
        Validations.isNotNullOrEmpty(name, 'environment name must be specified')
        Validations.isValidBambooEntityName(name, 'environment name must not contain special characters.')
        this.name = name
        this.id = id
    }

    protected Environment() {}

    /**
     * A description for this environment.
     *
     * @param description A description for this environment
     */
    void description(String description) {
        Validations.isSafeBambooString(description)
        this.description = description
    }

    /**
     * Tasks define the steps involved in deploying a release to the environment.
     */
    Tasks tasks(@DelegatesTo(value = Tasks, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def newTaskList = new Tasks(bambooFacade)
        DslScriptHelper.execute(closure, newTaskList)
        tasks = newTaskList
    }

    /**
     * Set triggers to specify how and when the deployment will be triggered automatically. When a deployment is
     * automatically triggered, a new release is created from the latest successful build result of the linked plan.
     *
     * @deprecated use {@link #triggers(Closure)} instead
     */
    @Deprecated
    DeploymentTriggers deploymentTriggers(
            @DelegatesTo(value = DeploymentTriggers, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        triggers(closure)
    }

    /**
     * Set triggers to specify how and when the deployment will be triggered automatically. When a deployment is
     * automatically triggered, a new release is created from the latest successful build result of the linked plan.
     */
    DeploymentTriggers triggers(@DelegatesTo(value = DeploymentTriggers, strategy = Closure.DELEGATE_FIRST) Closure c) {
        def triggers = new DeploymentTriggers()
        DslScriptHelper.execute(c, triggers)
        this.triggers = triggers
    }

    /**
     * Specifies the variables for this environment.
     *
     * @since 1.5.0
     */
    Variables variables(@DelegatesTo(value = Variables, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def vars = new Variables(bambooFacade)
        DslScriptHelper.execute(closure, vars)
        this.variables = vars
    }

    /**
     * Specifies the notifications for this environment.
     *
     * @since 1.5.0
     */
    EnvironmentNotifications notifications(
            @DelegatesTo(value = EnvironmentNotifications, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        notifications = new EnvironmentNotifications(bambooFacade)
        DslScriptHelper.execute(closure, notifications)
        notifications
    }

    /**
     * Specifies the notifications for this environment.
     *
     * @since 1.5.0
     */
    EnvironmentNotifications notifications() {
        notifications = new EnvironmentNotifications(bambooFacade)
        notifications
    }

    /**
     * Specifies the permissions for this environment.
     *
     * @since 1.5.1
     */
    Permissions permissions(@DelegatesTo(value = Permissions, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def permissions = new Permissions(bambooFacade)
        DslScriptHelper.execute(closure, permissions)
        this.permissions = permissions
    }
}
