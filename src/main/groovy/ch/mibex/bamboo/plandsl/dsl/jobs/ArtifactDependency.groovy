package ch.mibex.bamboo.plandsl.dsl.jobs

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class ArtifactDependency {
    String name
    String destinationDirectory

    /**
     * Creates an artifact dependency.
     *
     * @param name the name of the artifact dependency
     */
    ArtifactDependency(String name) {
        this.name = name
    }

    protected ArtifactDependency() {}

    /**
     * The destination directory for the dependency.
     */
    void destinationDirectory(String destinationDirectory) {
        this.destinationDirectory = destinationDirectory
    }

}
