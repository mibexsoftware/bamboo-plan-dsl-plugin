package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper

class Artifacts  {
    final Set<ArtifactDefinition> artifactDefinitions = new LinkedHashSet<>()
    final Set<ArtifactDependency> artifactDependencies = new LinkedHashSet<>()

    /**
     * Defines an artifact definition.
     *
     * @param name the name of the artifact definition
     */
    void definition(String name, @DelegatesTo(ArtifactDefinition) Closure closure) {
        def definition = new ArtifactDefinition(name)
        DslScriptHelper.execute(closure, definition)
        artifactDefinitions << definition
    }

    /**
     * Defines an artifact dependency.
     *
     * @param name the name of the artifact dependency
     */
    void dependency(String name, @DelegatesTo(ArtifactDependency) Closure closure) {
        def dependency = new ArtifactDependency(name)
        DslScriptHelper.execute(closure, dependency)
        artifactDependencies << dependency
    }
}
