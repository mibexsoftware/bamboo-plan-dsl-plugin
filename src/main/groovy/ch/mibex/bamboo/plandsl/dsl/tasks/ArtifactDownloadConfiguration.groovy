package ch.mibex.bamboo.plandsl.dsl.tasks

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class ArtifactDownloadConfiguration {
    final String name
    String destinationPath

    ArtifactDownloadConfiguration(String name) {
        this.name = name
    }

    void destinationPath(String destinationPath) {
        this.destinationPath = destinationPath
    }

}
