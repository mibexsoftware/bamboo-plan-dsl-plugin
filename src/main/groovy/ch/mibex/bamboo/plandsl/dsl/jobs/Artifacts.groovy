package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Artifacts extends BambooObject {
    private final List<ArtifactDefinition> artifactDefinitions = []
    private final List<ArtifactDependency> artifactDependencies = []

    Artifacts(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Defines an artifact definition.
     *
     * @param name the name of the artifact definition
     * @deprecated use {@link #definition(Map, Closure)} instead
     */
    @Deprecated
    void definition(String name,
                    @DelegatesTo(value = ArtifactDefinition, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def definition = new ArtifactDefinition(name, bambooFacade)
        DslScriptHelper.execute(closure, definition)
        artifactDefinitions << definition
    }

    /**
     * Defines an artifact definition.
     *
     * @param name the name of the artifact definition
     * @param copyPattern the name (or Ant file copy pattern) of the artifact(s) you want to keep,
     * e.g. &#42;&#42;&#47;&#42;.jar
     */
    void definition(String name,
                    String copyPattern,
                    @DelegatesTo(value = ArtifactDefinition, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def definition = new ArtifactDefinition(name, copyPattern, bambooFacade)
        DslScriptHelper.execute(closure, definition)
        artifactDefinitions << definition
    }

    /**
     * Defines an artifact definition.
     *
     * @param params the mandatory parameters for an artifact definition.
     * Currently, "name" and "copyPattern" are expected.
     */
    void definition(Map<String, String> params,
                    @DelegatesTo(value = ArtifactDefinition, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        definition(params['name'], params['copyPattern'], closure)
    }

    /**
     * Defines an artifact dependency.
     *
     * @param name the name of the artifact dependency
     */
    void dependency(String name,
                    @DelegatesTo(value = ArtifactDependency, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def dependency = new ArtifactDependency(name, bambooFacade)
        DslScriptHelper.execute(closure, dependency)
        artifactDependencies << dependency
    }

    /**
     * Defines an artifact dependency.
     *
     * @param params the mandatory parameters for an artifact depdencency. Currently, only "name" is expected.
     */
    void dependency(Map<String, String> params,
                    @DelegatesTo(value = ArtifactDependency, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        dependency(params['name'], closure)
    }
}
