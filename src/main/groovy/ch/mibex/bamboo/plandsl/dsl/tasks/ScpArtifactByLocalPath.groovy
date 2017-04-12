package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class ScpArtifactByLocalPath extends BambooObject {
    private boolean useAntPatternsToSelectFiles

    ScpArtifactByLocalPath(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing
    protected ScpArtifactByLocalPath() {}

    /**
     * Use Ant patterns to select files. See the Ant pattern reference.
     */
    void useAntPatternsToSelectFiles(boolean enabled = true) {
        this.useAntPatternsToSelectFiles = enabled
    }

}
