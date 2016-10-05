package ch.mibex.bamboo.plandsl.dsl.scm.options

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class AdvancedHgMercurialOptions extends AdvancedHgOptions {
    boolean enableCommitIsolation
    boolean disableRepositoryCaching

    void enableCommitIsolation(boolean enableCommitIsolation) {
        this.enableCommitIsolation = enableCommitIsolation
    }

    void disableRepositoryCaching(boolean disableRepositoryCaching) {
        this.disableRepositoryCaching = disableRepositoryCaching
    }
}
