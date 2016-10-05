package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import ch.mibex.bamboo.plandsl.dsl.tasks.Tasks
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@ToString
@EqualsAndHashCode
class Environment {
    String name
    String description
    Tasks tasks = new Tasks()
    DeploymentTriggers triggers = new DeploymentTriggers()

    Environment(String name) {
        Validations.isNotNullOrEmpty(name, 'environment name must be specified')
        Validations.isValidBambooEntityName(name, 'environment name must not contain special characters.')
        this.name = name
    }

    protected Environment() {}

    void description(String description) {
        Validations.isSafeBambooString(description)
        this.description = description
    }

    void tasks(@DelegatesTo(Tasks) Closure closure) {
        def newTaskList = new Tasks()
        DslScriptHelper.execute(closure, newTaskList)
        tasks = newTaskList
    }

    void triggers(@DelegatesTo(DeploymentTriggers) Closure closure) {
        def triggers = new DeploymentTriggers()
        DslScriptHelper.execute(closure, triggers)
        this.triggers = triggers
    }

}
