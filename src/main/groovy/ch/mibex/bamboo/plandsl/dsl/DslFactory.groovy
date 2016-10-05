package ch.mibex.bamboo.plandsl.dsl

interface DslFactory {

    /**
     * Creates a project definition.
     *
     * @param key the key of the project consisting of 2 or more upper case alphanumeric characters
     */
    Project project(String key, @DelegatesTo(Project) Closure closure)

}
