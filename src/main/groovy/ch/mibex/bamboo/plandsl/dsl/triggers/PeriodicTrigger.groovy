package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class PeriodicTrigger extends BambooObject {
    private int pollingFrequencyInSecs

    // for tests
    protected PeriodicTrigger() {}

    protected PeriodicTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * How often (in seconds) should Bamboo check the repository for changes?
     */
    void pollingFrequencyInSecs(int frequency) {
        this.pollingFrequencyInSecs = frequency
    }
}
