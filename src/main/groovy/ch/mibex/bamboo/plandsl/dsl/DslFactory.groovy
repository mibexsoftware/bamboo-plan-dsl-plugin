package ch.mibex.bamboo.plandsl.dsl

interface DslFactory {

    /**
     * Creates a project definition.
     *
     * @param key the key of the project consisting of 2 or more upper case alphanumeric characters
     * @deprecated use {@link #project(Map, Closure)} instead
     */
    @Deprecated
    Project project(String key, @DelegatesTo(value = Project, strategy = Closure.DELEGATE_FIRST) Closure closure)

    /**
     * Creates a project definition.
     *
     * @param key the key of the project consisting of 2 or more upper case alphanumeric characters
     * @param key the name of the project
     */
    Project project(String key, String name,
                    @DelegatesTo(value = Project, strategy = Closure.DELEGATE_FIRST) Closure closure)

    /**
     * Creates a project definition.
     *
     * @param projectParams a map with the mandatory project properties. Currently, "key" and "name" are expected.
     */
    Project project(Map<String, String> projectParams,
                    @DelegatesTo(value = Project, strategy = Closure.DELEGATE_FIRST) Closure closure)

}
