package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class PollingTrigger extends TriggerType {
    List<String> repositories
    String pollingStrategy
    ScheduledTrigger scheduledTrigger
    PeriodicTrigger periodicTrigger

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

    void repositories(String... repositories) {
        this.repositories = repositories
    }

}
