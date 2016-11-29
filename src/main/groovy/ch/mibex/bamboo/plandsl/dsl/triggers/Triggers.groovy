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
    @Deprecated
    void bitbucketServerRepositoryTriggered(String description,
                                            @DelegatesTo(BitbucketServerTrigger) Closure closure) {
        def trigger = new BitbucketServerTrigger(bambooFacade)
        trigger.description = description
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }
    /**
     * Bitbucket Server triggers the build when changes are committed.
     */
    void bitbucketServerRepositoryTriggered(@DelegatesTo(BitbucketServerTrigger) Closure closure) {
        def trigger = new BitbucketServerTrigger(bambooFacade)
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    /**
     * Run according to schedule.
     */
    @Deprecated
    void scheduled(String description, @DelegatesTo(ScheduledTrigger) Closure closure) {
        def scheduled = new ScheduledTrigger(bambooFacade)
        scheduled.description = description
        DslScriptHelper.execute(closure, scheduled)
        triggers << scheduled
    }

    /**
     * Run according to schedule.
     */
    void scheduled(@DelegatesTo(ScheduledTrigger) Closure closure) {
        scheduled(null, closure)
    }

    /**
     * Manual trigger.
     */
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
    @Deprecated
    void polling(String description, @DelegatesTo(PollingTrigger) Closure closure) {
        def trigger = new PollingTrigger(bambooFacade)
        trigger.description = description
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    /**
     * Repository triggers the build when changes are committed.
     */
    @Deprecated
    void remote(String description, @DelegatesTo(RemoteTrigger) Closure closure) {
        def trigger = new RemoteTrigger(bambooFacade)
        trigger.description = description
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    /**
     * Repository triggers the build when changes are committed.
     */
    void remote(@DelegatesTo(RemoteTrigger) Closure closure) {
        remote(null, closure)
    }

    /**
     * Single daily build.
     */
    @Deprecated
    void onceAday(String description, @DelegatesTo(OnceADayTrigger) Closure closure) {
        def trigger = new OnceADayTrigger(bambooFacade)
        trigger.description = description
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
