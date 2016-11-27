package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class BitbucketServerTrigger extends TriggerType {

    // for tests
    protected BitbucketServerTrigger() {}

    protected BitbucketServerTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }
}
