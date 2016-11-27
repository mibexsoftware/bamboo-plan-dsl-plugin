package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class QuietPeriod extends BambooObject {
    private int waitTimeInSeconds
    private int maximumRetries

    QuietPeriod(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected QuietPeriod() {}

    /**
     * How many seconds should Bamboo wait after a new change, before initiating a build?
     */
    void waitTimeInSeconds(int waitTimeInSeconds) {
        this.waitTimeInSeconds = waitTimeInSeconds
    }

    /**
     * How many times should Bamboo check for new changes before initiating a build regardless?
     */
    void maximumRetries(int maximumRetries) {
        this.maximumRetries = maximumRetries
    }

}
