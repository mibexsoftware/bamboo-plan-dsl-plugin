package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class CloverOptions extends BambooObject {
    private boolean generateCloverHistoricalReport
    private boolean generateJSONReport

    // just for testing
    protected CloverOptions() {}

    CloverOptions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Include coverage trends and class movers in the Clover HTML report. (Will only work consistently if this plan
     * is run on a single agent and no clean checkout is performed.)
     */
    void generateCloverHistoricalReport(boolean generate = true) {
        generateCloverHistoricalReport = generate
    }

    /**
     * JSON makes it very easy to integrate Clover data into a web-page.
     */
    void generateJSONReport(boolean generate = true) {
        generateJSONReport = generate
    }
}
