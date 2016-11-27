package ch.mibex.bamboo.plandsl.dsl.scm.web

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class BitbucketWebRepository extends WebRepositoryType {

    BitbucketWebRepository(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing
    protected BitbucketWebRepository() {}
}
