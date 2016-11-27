package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import ch.mibex.bamboo.plandsl.dsl.tasks.Tasks
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class Environment extends BambooObject {
    private String name
    private String description
    private Tasks tasks = new Tasks(bambooFacade)
    private DeploymentTriggers triggers = new DeploymentTriggers(bambooFacade)

    Environment(String name, BambooFacade bambooFacade) {
        super(bambooFacade)
        Validations.isNotNullOrEmpty(name, 'environment name must be specified')
        Validations.isValidBambooEntityName(name, 'environment name must not contain special characters.')
        this.name = name
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
    void tasks(@DelegatesTo(Tasks) Closure closure) {
        def newTaskList = new Tasks(bambooFacade)
        DslScriptHelper.execute(closure, newTaskList)
        tasks = newTaskList
    }

    /**
     * Set triggers to specify how and when the deployment will be triggered automatically. When a deployment is
     * automatically triggered, a new release is created from the latest successful build result of the linked plan.
     */
    void deploymentTriggers(@DelegatesTo(DeploymentTriggers) Closure closure) {
        def triggers = new DeploymentTriggers()
        DslScriptHelper.execute(closure, triggers)
        this.triggers = triggers
    }

    // deprecated because IntelliJ confused this with plan#triggers
    @Deprecated
    void triggers(@DelegatesTo(DeploymentTriggers) Closure closure) {
        deploymentTriggers(closure)
    }

}
