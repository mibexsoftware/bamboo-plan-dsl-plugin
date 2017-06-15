package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Notifications extends AbstractNotifications {
    protected Notifications() {}

    Notifications(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * This notification type is obsolete and should no longer be used. Bamboo notifies Bitbucket Server about
     * every build result automatically.
     */
    @Deprecated
    void stashLegacy(NotificationEvent event,
                     @DelegatesTo(value = StashLegacyNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new StashLegacyNotification(event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify responsible users.
     */
    @Deprecated
    void responsibleUsers(
            NotificationEvent event,
            @DelegatesTo(value = ResponsibleUsersNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new ResponsibleUsersNotification(event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify responsible users.
     *
     * @param params The mandatory parameters of this notification. "event" is expected.
     */
    void responsibleUsers(
            Map<String, Object> params,
            @DelegatesTo(value = ResponsibleUsersNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        responsibleUsers(params['event'] as NotificationEvent, closure)
    }

    /**
     * Notify users who have committed to the build.
     *
     * @deprecated use {@link #committers(Map, Closure)} instead
     */
    @Deprecated
    void committers(NotificationEvent event,
                    @DelegatesTo(value = CommittersNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new CommittersNotification(event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify users who have committed to the build.
     */
    void committers(Map<String, Object> params,
                    @DelegatesTo(value = CommittersNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        committers(params['event'] as NotificationEvent, closure)
    }

    /**
     * Notify users who have marked this build as their favourite.
     *
     * @deprecated use {@link #watchers(Map, Closure)} instead
     */
    @Deprecated
    void watchers(NotificationEvent event,
                  @DelegatesTo(value = WatchersNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new WatchersNotification(event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify users who have marked this build as their favourite.
     */
    void watchers(Map<String, Object> params,
                  @DelegatesTo(value = CommittersNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        watchers(params['event'] as NotificationEvent, closure)
    }

    /**
     * A custom (i.e., not built-in) notification.
     */
    void custom(NotificationEvent event,
                String pluginKey,
                @DelegatesTo(value = CustomNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new CustomNotification(pluginKey, event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * A custom (i.e., not built-in) notification.
     *
     * @param params The mandatory parameters of this notification. "event" and "pluginKey" are expected.
     */
    void custom(Map<String, Object> params,
                @DelegatesTo(value = CustomNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        custom(params['event'] as NotificationEvent, params['pluginKey'] as String, closure)
    }

    static enum NotificationEvent {
        /* plan notification events */
        ALL_BUILDS_COMPLETED('com.atlassian.bamboo.plugin.system.notifications:chainCompleted.allBuilds'),
        CHANGE_OF_BUILD_STATUS('com.atlassian.bamboo.plugin.system.notifications:chainCompleted.changedChainStatus'),
        FAILED_BUILDS_AND_FIRST_SUCCESSFUL(
                'com.atlassian.bamboo.plugin.system.notifications:chainCompleted.failedChains'
        ),
        AFTER_X_BUILD_FAILURES('com.atlassian.bamboo.plugin.system.notifications:chainCompleted.XFailedChains'),
        COMMENT_ADDED('com.atlassian.bamboo.plugin.system.notifications:buildCommented'),
        CHANGE_OF_RESPONSIBILITIES('com.atlassian.bamboo.brokenbuildtracker:responsibilityChanged'),
        ALL_JOBS_COMPLETED('com.atlassian.bamboo.plugin.system.notifications:buildCompleted.allBuilds'),
        CHANGE_OF_JOB_STATUS('com.atlassian.bamboo.plugin.system.notifications:buildCompleted.changedBuildStatus'),
        FAILED_JOBS_AND_FIRST_SUCCESSFUL(
                'com.atlassian.bamboo.plugin.system.notifications:buildCompleted.failedBuilds'
        ),
        FIRST_FAILED_JOB_FOR_PLAN('com.atlassian.bamboo.plugin.system.notifications:buildCompleted.firstJobFailed'),
        JOB_ERROR('com.atlassian.bamboo.plugin.system.notifications:buildError'),
        JOB_HUNG('com.atlassian.bamboo.plugin.system.notifications:buildHung'),
        JOB_QUEUE_TIMEOUT('com.atlassian.bamboo.plugin.system.notifications:buildQueueTimeout'),
        JOB_QUEUE_WITHOUT_CAPABLE_AGENTS('com.atlassian.bamboo.plugin.system.notifications:buildMissingCapableAgent'),

        /* deployment project notification events */
        DEPLOYMENT_STARTED_AND_FINISHED('bamboo.deployments:deploymentStartedFinished'),
        DEPLOYMENT_FINISHED('bamboo.deployments:deploymentFinished'),
        DEPLOYMENT_FAILED('bamboo.deployments:deploymentFailed')

        String bambooNotificationConditionKey

        NotificationEvent(String bambooNotificationConditionKey) {
            this.bambooNotificationConditionKey = bambooNotificationConditionKey
        }
    }

}
