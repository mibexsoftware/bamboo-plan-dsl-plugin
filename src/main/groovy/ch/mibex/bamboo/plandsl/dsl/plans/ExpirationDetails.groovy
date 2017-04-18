package ch.mibex.bamboo.plandsl.dsl.plans

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class ExpirationDetails extends BambooObject {
    private String[] keepBuildsWithLabels
    private int expireAfter
    private TimeUnit expireTimeUnit
    private int minimumBuildsToKeep
    private boolean expireBuildResults
    private boolean expireBuildArtifacts
    private boolean expireBuildLogs

    // just for testing
    protected ExpirationDetails() {}

    ExpirationDetails(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Expire builds that completed before the above time period. Use 0 to ignore this option.
     *
     * @param amount
     * @param timeUnit
     */
    void expireAfter(int amount, TimeUnit timeUnit) {
        expireAfter = amount
        expireTimeUnit = timeUnit
    }

    /**
     * '1' will keep the last build on a plan, the rest will expire according to your expiry time frame.
     *  Leave blank if not needed.
     *
     *  @param buildsPerPlan build(s) per plan
     */
    void minimumBuildsToKeep(int buildsPerPlan) {
        minimumBuildsToKeep = buildsPerPlan
    }

    /**
     * Enter multiple labels separated by spaces.
     */
    void keepBuildsWithTheFollowingLabels(String... labels) {
        keepBuildsWithLabels = labels
    }

    /**
     * The entire result will be removed (including artifacts).
     */
    void expireBuildResults(boolean enabled = true) {
        expireBuildResults = enabled
    }

    /**
     * User defined artifacts will be expired.
     */
    void expireBuildArtifacts(boolean enabled = true) {
        expireBuildArtifacts = enabled
    }

    /**
     * Build log will be expired.
     */
    void expireBuildLogs(boolean enabled = true) {
        expireBuildLogs = enabled
    }

    static enum TimeUnit {
        DAYS("days"), WEEKS("weeks"), MONTHS("months")

        String value

        TimeUnit(String value) {
            this.value = value
        }

        @Override
        public String toString() {
            this.value
        }
    }
}
