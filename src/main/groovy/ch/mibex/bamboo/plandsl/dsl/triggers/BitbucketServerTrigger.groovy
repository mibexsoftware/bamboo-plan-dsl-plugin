package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class BitbucketServerTrigger extends TriggerType {
    private List<String> repositories

    // for tests
    protected BitbucketServerTrigger() {}

    // necessary for YAML !bitbucketServerTrigger single-arg ctor
    protected BitbucketServerTrigger(String s) {
    }

    protected BitbucketServerTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Which repositories should the trigger apply to?
     *
     * @since 1.9.2
     */
    void repositories(String... repositories) {
        this.repositories = repositories
    }
}
