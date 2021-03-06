package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class PollingTrigger extends TriggerType {
    private List<String> repositories
    private ScheduledTrigger scheduledTrigger
    private PeriodicTrigger periodicTrigger

    // for tests
    protected PollingTrigger() {}

    protected PollingTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void scheduled(@DelegatesTo(value = ScheduledTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def trigger = new ScheduledTrigger()
        DslScriptHelper.execute(closure, trigger)
        this.scheduledTrigger = trigger
    }

    void periodically(@DelegatesTo(value = PeriodicTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def trigger = new PeriodicTrigger()
        DslScriptHelper.execute(closure, trigger)
        this.periodicTrigger = trigger
    }

    /**
     * Which repositories should the trigger apply to?
     */
    void repositories(String... repositories) {
        this.repositories = repositories
    }
}
