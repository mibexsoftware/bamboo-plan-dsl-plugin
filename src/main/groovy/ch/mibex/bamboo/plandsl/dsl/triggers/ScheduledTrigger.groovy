package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class ScheduledTrigger extends TriggerType {
    private cronExpression

    // for tests
    protected ScheduledTrigger() {}

    protected ScheduledTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Cron expression.
     */
    void cronExpression(String cronExpression) {
        this.cronExpression = cronExpression
    }

}
