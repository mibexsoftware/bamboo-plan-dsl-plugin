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
     *
     * @deprecated use {@link #bitbucketServerRepositoryTriggered(Closure)} instead
     */
    @Deprecated
    void bitbucketServerRepositoryTriggered(
            String description,
            @DelegatesTo(value = BitbucketServerTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def trigger = new BitbucketServerTrigger(bambooFacade)
        trigger.description = description
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }
    /**
     * Bitbucket Server triggers the build when changes are committed.
     */
    void bitbucketServerRepositoryTriggered(
            @DelegatesTo(value = BitbucketServerTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def trigger = new BitbucketServerTrigger(bambooFacade)
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    /**
     * Run according to schedule.
     *
     * @deprecated use {@link #scheduled(Closure)} instead
     */
    @Deprecated
    void scheduled(String description,
                   @DelegatesTo(value = ScheduledTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def scheduled = new ScheduledTrigger(bambooFacade)
        scheduled.description = description
        DslScriptHelper.execute(closure, scheduled)
        triggers << scheduled
    }

    /**
     * Run according to schedule.
     */
    void scheduled(@DelegatesTo(value = ScheduledTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
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
    void polling(@DelegatesTo(value = PollingTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        polling(null, closure)
    }

    /**
     * Bamboo polls source repository and builds when new changes are found.
     *
     * @deprecated use {@link #polling(Closure)} instead
     */
    @Deprecated
    void polling(String description,
                 @DelegatesTo(value = PollingTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def trigger = new PollingTrigger(bambooFacade)
        trigger.description = description
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    /**
     * Repository triggers the build when changes are committed.
     *
     * @deprecated use {@link #remote(Closure)} instead
     */
    @Deprecated
    void remote(String description,
                @DelegatesTo(value = RemoteTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def trigger = new RemoteTrigger(bambooFacade)
        trigger.description = description
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    /**
     * Repository triggers the build when changes are committed.
     */
    void remote(@DelegatesTo(value = RemoteTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        remote(null, closure)
    }

    /**
     * Single daily build.
     *
     * @deprecated use {@link #onceAday(Closure)} instead
     */
    @Deprecated
    void onceAday(String description,
                  @DelegatesTo(value = OnceADayTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def trigger = new OnceADayTrigger(bambooFacade)
        trigger.description = description
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    /**
     * Single daily build.
     */
    void onceAday(@DelegatesTo(value = OnceADayTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        onceAday(null, closure)
    }

}
