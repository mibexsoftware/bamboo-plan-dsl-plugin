package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class PollingTrigger extends TriggerType {
    private List<String> repositories
    private String pollingStrategy
    private ScheduledTrigger scheduledTrigger
    private PeriodicTrigger periodicTrigger

    // for tests
    protected PollingTrigger() {}

    protected PollingTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void scheduled(@DelegatesTo(ScheduledTrigger) Closure closure) {
        def trigger = new ScheduledTrigger()
        DslScriptHelper.execute(closure, trigger)
        this.pollingStrategy = 'CRON'
        this.scheduledTrigger = trigger
    }

    void periodically(@DelegatesTo(PeriodicTrigger) Closure closure) {
        def trigger = new PeriodicTrigger()
        DslScriptHelper.execute(closure, trigger)
        this.periodicTrigger = trigger
        this.pollingStrategy = 'PERIOD'
    }

    /**
     * Which repositories should the trigger apply to?
     */
    void repositories(String... repositories) {
        this.repositories = repositories
    }

}
