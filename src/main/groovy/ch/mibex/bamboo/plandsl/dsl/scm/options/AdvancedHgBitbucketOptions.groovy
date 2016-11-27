package ch.mibex.bamboo.plandsl.dsl.scm.options

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class AdvancedHgBitbucketOptions extends AdvancedHgOptions {
    private boolean fetchWholeRepository

    AdvancedHgBitbucketOptions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected AdvancedHgBitbucketOptions() {}

    void fetchWholeRepository(boolean fetchWholeRepository = true) {
        this.fetchWholeRepository = fetchWholeRepository
    }
}
