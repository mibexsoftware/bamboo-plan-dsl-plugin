package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.DslParentElement
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class Triggers implements DslParentElement<TriggerType> {
    Set<TriggerType> triggers = new LinkedHashSet<>()

    void scheduled(String displayName, @DelegatesTo(ScheduledTrigger) Closure closure) {
        def scheduled = new ScheduledTrigger()
        scheduled.displayName = displayName
        DslScriptHelper.execute(closure, scheduled)
        triggers << scheduled
    }

    void bitbucketServerRepositoryTriggered(String displayName, @DelegatesTo(BitbucketServerTrigger) Closure closure) {
        def trigger = new BitbucketServerTrigger()
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    void scheduled(@DelegatesTo(ScheduledTrigger) Closure closure) {
        scheduled(null, closure)
    }

    void manual() {
        def trigger = new ManualTrigger()
        triggers << trigger
    }

    void polling(@DelegatesTo(PollingTrigger) Closure closure) {
        polling(null, closure)
    }

    void polling(String displayName, @DelegatesTo(PollingTrigger) Closure closure) {
        def trigger = new PollingTrigger()
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    void remote(String displayName, @DelegatesTo(RemoteTrigger) Closure closure) {
        def trigger = new RemoteTrigger()
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    void remote(@DelegatesTo(RemoteTrigger) Closure closure) {
        polling(null, closure)
    }

    void onceAday(String displayName, @DelegatesTo(OnceADayTrigger) Closure closure) {
        def trigger = new OnceADayTrigger()
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    void onceAday(@DelegatesTo(OnceADayTrigger) Closure closure) {
        onceAday(null, closure)
    }

    @Override
    Collection<TriggerType> children() {
        triggers
    }

}
