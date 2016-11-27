package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class ArtifactDownloadConfiguration extends BambooObject {
    private final String name
    private String destinationPath

    ArtifactDownloadConfiguration(String name, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.name = name
    }

    /**
     * Location that artifacts will be downloaded to, relative to the working directory.
     */
    void destinationPath(String destinationPath) {
        this.destinationPath = destinationPath
    }

}
