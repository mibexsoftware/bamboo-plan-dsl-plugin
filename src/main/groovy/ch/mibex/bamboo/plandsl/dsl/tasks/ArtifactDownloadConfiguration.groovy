package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class ArtifactDownloadConfiguration extends BambooObject {
    private String name
    private String destinationPath
    private String sourcePlanKey

    ArtifactDownloadConfiguration(String name, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.name = name
    }

    // for testing
    protected ArtifactDownloadConfiguration() {
    }

    /**
     * Location that artifacts will be downloaded to, relative to the working directory.
     */
    void destinationPath(String destinationPath) {
        this.destinationPath = destinationPath
    }

    /**
     * Plan where artifact is produced. If not set, the current plan will be used.
     *
     * @since 1.4.1
     */
    void sourcePlanKey(String sourcePlanKey) {
        this.sourcePlanKey = sourcePlanKey
    }

}
