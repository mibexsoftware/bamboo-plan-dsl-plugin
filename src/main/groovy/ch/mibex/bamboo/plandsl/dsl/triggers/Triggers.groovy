package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Triggers extends BambooObject {
    private List<TriggerType> triggers = []

    // for tests
    protected Triggers() {}

    protected Triggers(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Bitbucket Server triggers the build when changes are committed.
     */
    void bitbucketServerRepositoryTriggered(String displayName,
                                            @DelegatesTo(BitbucketServerTrigger) Closure closure) {
        def trigger = new BitbucketServerTrigger(bambooFacade)
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    /**
     * Run according to schedule.
     */
    void scheduled(String displayName, @DelegatesTo(ScheduledTrigger) Closure closure) {
        def scheduled = new ScheduledTrigger(bambooFacade)
        scheduled.displayName = displayName
        DslScriptHelper.execute(closure, scheduled)
        triggers << scheduled
    }

    /**
     * Run according to schedule.
     */
    void scheduled(@DelegatesTo(ScheduledTrigger) Closure closure) {
        scheduled(null, closure)
    }

    void manual() {
        def trigger = new ManualTrigger()
        triggers << trigger
    }

    /**
     * Bamboo polls source repository and builds when new changes are found.
     */
    void polling(@DelegatesTo(PollingTrigger) Closure closure) {
        polling(null, closure)
    }

    /**
     * Bamboo polls source repository and builds when new changes are found.
     */
    void polling(String displayName, @DelegatesTo(PollingTrigger) Closure closure) {
        def trigger = new PollingTrigger(bambooFacade)
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    /**
     * Repository triggers the build when changes are committed.
     */
    void remote(String displayName, @DelegatesTo(RemoteTrigger) Closure closure) {
        def trigger = new RemoteTrigger(bambooFacade)
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    /**
     * Repository triggers the build when changes are committed.
     */
    void remote(@DelegatesTo(RemoteTrigger) Closure closure) {
        polling(null, closure)
    }

    /**
     * Single daily build.
     */
    void onceAday(String displayName, @DelegatesTo(OnceADayTrigger) Closure closure) {
        def trigger = new OnceADayTrigger(bambooFacade)
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    /**
     * Single daily build.
     */
    void onceAday(@DelegatesTo(OnceADayTrigger) Closure closure) {
        onceAday(null, closure)
    }

}
