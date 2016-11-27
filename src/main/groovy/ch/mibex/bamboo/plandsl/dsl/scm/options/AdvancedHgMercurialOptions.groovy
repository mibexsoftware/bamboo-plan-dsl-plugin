package ch.mibex.bamboo.plandsl.dsl.scm.options

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class AdvancedHgMercurialOptions extends AdvancedHgOptions {
    private boolean enableCommitIsolation
    private boolean disableRepositoryCaching

    AdvancedHgMercurialOptions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected AdvancedHgMercurialOptions() {}

    /**
     * Ensures that a build will only have one change, allowing you to isolate your build failures.
     */
    void enableCommitIsolation(boolean enableCommitIsolation = true) {
        this.enableCommitIsolation = enableCommitIsolation
    }

    /**
     * Disable repository caching to enable subrepositories support.
     */
    void disableRepositoryCaching(boolean disableRepositoryCaching = true) {
        this.disableRepositoryCaching = disableRepositoryCaching
    }
}
