package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.deployprojs.DeploymentProject
import ch.mibex.bamboo.plandsl.dsl.jobs.Job

interface DslFactory {
    /**
     * Creates a project definition.
     *
     * @param key the key of the project consisting of 2 or more upper case alphanumeric characters
     */
    @Deprecated
    Project project(String key, @DelegatesTo(Project) Closure closure)

    /**
     * Creates a project definition.
     *
     * @param key the key of the project consisting of 2 or more upper case alphanumeric characters
     * @param key the name of the project
     */
    Project project(String key, String name, @DelegatesTo(Project) Closure closure)

    /**
     * Creates a project definition.
     *
     * @param projectParams a map with the mandatory project properties. Currently, "key" and "name" are expected.
     */
    Project project(Map<String, String> projectParams, @DelegatesTo(Project) Closure closure)

    /**
     * Creates a plan definition.
     *
     * @param key the key of the plan consisting of 2 or more upper case alphanumeric characters
     * @param name the name of the build plan
     */
    @Deprecated
    Plan plan(String key, String name)

    /**
     * Creates a plan definition.
     *
     * @param params a map with the mandatory plan properties. Currently, "key" and "name" are expected.
     */
    @Deprecated
    Plan plan(Map<String, String> params)

    /**
     * Creates a stage definition.
     *
     * @param name the name of the job
     */
    @Deprecated
    Stage stage(String name)

    /**
     * Creates a job definition.
     *
     * @param params a map with the mandatory stage properties. Currently, only "name" is expected.
     */
    @Deprecated
    Stage stage(Map<String, String> params)

    /**
     * Creates a job definition.
     *
     * @param key the key of the job consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters. E. g. CORE (for a module called core)
     * @param name the name of the job
     */
    @Deprecated
    Job job(String key, String name)

    /**
     * Creates a job definition.
     *
     * @param params a map with the mandatory job properties. Currently, "key" and "name" are expected.
     */
    @Deprecated
    Job job(Map<String, String> params)

    /**
     * Creates a deployment project definition.
     *
     * @param name the name of the deployment project
     */
    @Deprecated
    DeploymentProject deploymentProject(String name)

    /**
     * Creates a deployment project definition.
     *
     * @param params a map with the mandatory deployment project properties. Currently, only "name" is expected.
     */
    @Deprecated
    DeploymentProject deploymentProject(Map<String, String> params)

    /**
     * Returns the Bamboo environment with all Bamboo variables.
     */
    @Deprecated
    BambooEnvironment env()

    /**
     * Returns the value for the given key of a Bamboo variable.
     *
     * @param key the key of the Bamboo variable
     */
    @Deprecated
    String env(String key)
}
