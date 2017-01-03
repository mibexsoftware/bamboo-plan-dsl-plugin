package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class BuildHungOptions extends BambooObject {
    private double buildTimeMultiplier
    private int logQuietTime
    private int buildQueueTimeout

    // just for testing
    protected BuildHungOptions() {}

    BuildHungOptions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Multiplier to be applied to the average build time (e.g. 2.5).
     */
    void buildTimeMultiplier(double buildTimeMultiplier) {
        this.buildTimeMultiplier = buildTimeMultiplier
    }

    /**
     * The length of time (in whole minutes) without logs being received (e.g. 10 minutes)
     */
    void logQuietTimeInMinutes(int logQuietTime) {
        this.logQuietTime = logQuietTime
    }

    /**
     * The length of time (in whole minutes) without logs being received (e.g. 10 minutes)
     */
    @Deprecated
    void logQuietTime(int logQuietTime) {
        logQuietTimeInMinutes(logQuietTime)
    }

    /**
     * Bamboo uses queue timeout to determine build waiting in the queue longer then expected.
     * The length of time (in whole minutes) before queue timeout would be detected (e.g. 60 minutes)
     */
    @Deprecated
    void buildQueueTimeout(int buildQueueTimeout) {
        buildQueueTimeoutInMinutes(buildQueueTimeout)
    }

    /**
     * Bamboo uses queue timeout to determine build waiting in the queue longer then expected.
     * The length of time (in whole minutes) before queue timeout would be detected (e.g. 60 minutes)
     */
    void buildQueueTimeoutInMinutes(int buildQueueTimeout) {
        this.buildQueueTimeout = buildQueueTimeout
    }
}
