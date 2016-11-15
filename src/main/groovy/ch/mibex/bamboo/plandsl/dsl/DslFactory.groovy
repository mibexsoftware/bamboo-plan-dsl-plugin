package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.jobs.Job

interface DslFactory {

    /**
     * Creates a project definition.
     *
     * @param key the key of the project consisting of 2 or more upper case alphanumeric characters
     */
    Project project(String key, @DelegatesTo(Project) Closure closure)

    /**
     * Creates a build job definition.
     *
     * @param key the key of the job consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters. E. g. CORE (for a module called core)
     */
    Job job(String key, @DelegatesTo(Job) Closure closure)

    Job job(String key)
}
