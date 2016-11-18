package ch.mibex.bamboo.plandsl.dsl.scm.options

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class AdvancedHgMercurialOptions extends AdvancedHgOptions {
    boolean enableCommitIsolation
    boolean disableRepositoryCaching

    void enableCommitIsolation(boolean enableCommitIsolation = true) {
        this.enableCommitIsolation = enableCommitIsolation
    }

    void disableRepositoryCaching(boolean disableRepositoryCaching = true) {
        this.disableRepositoryCaching = disableRepositoryCaching
    }
}
