package ch.mibex.bamboo.plandsl.dsl.scm.options

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class AdvancedHgBitbucketOptions extends AdvancedHgOptions {
    boolean fetchWholeRepository

    void fetchWholeRepository(boolean fetchWholeRepository = true) {
        this.fetchWholeRepository = fetchWholeRepository
    }
}
