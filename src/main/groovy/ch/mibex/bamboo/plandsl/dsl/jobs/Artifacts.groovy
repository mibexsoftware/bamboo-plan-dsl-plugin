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
     */
    void definition(String name, @DelegatesTo(ArtifactDefinition) Closure closure) {
        def definition = new ArtifactDefinition(name, bambooFacade)
        DslScriptHelper.execute(closure, definition)
        artifactDefinitions << definition
    }

    /**
     * Defines an artifact dependency.
     *
     * @param name the name of the artifact dependency
     */
    void dependency(String name, @DelegatesTo(ArtifactDependency) Closure closure) {
        def dependency = new ArtifactDependency(name, bambooFacade)
        DslScriptHelper.execute(closure, dependency)
        artifactDependencies << dependency
    }
}
