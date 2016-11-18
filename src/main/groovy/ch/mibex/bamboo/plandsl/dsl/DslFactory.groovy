package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.deployprojs.DeploymentProject
import ch.mibex.bamboo.plandsl.dsl.jobs.Job

interface DslFactory {
    /**
     * Creates a project definition.
     *
     * @param key the key of the project consisting of 2 or more upper case alphanumeric characters
     */
    Project project(String key, @DelegatesTo(Project) Closure closure)

    Plan plan(String key)

    Job job(String key)

    Stage stage(String name)

    DeploymentProject deploymentProject(String name)

    BambooEnvironment env()

    String env(String key)
}
