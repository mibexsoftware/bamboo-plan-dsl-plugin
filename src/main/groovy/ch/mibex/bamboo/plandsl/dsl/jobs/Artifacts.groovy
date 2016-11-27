package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper

class Artifacts extends BambooObject {
    private final List<ArtifactDefinition> artifactDefinitions = new ArrayList<>()
    private final List<ArtifactDependency> artifactDependencies = new ArrayList<>()

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
