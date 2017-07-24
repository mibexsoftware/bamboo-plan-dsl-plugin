package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Notifications extends BambooObject {
    private List<NotificationType> notifications = []

    protected Notifications() {}

    Notifications(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Notify a user.
     *
     * @deprecated use {@link #user(Map, Closure)} instead
     */
    @Deprecated
    void user(NotificationEvent event,
              @DelegatesTo(value = UserNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new UserNotification(event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify a user.
     */
    void user(Map<String, Object> params,
              @DelegatesTo(value = UserNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        user(params['event'] as NotificationEvent, closure)
    }

    /**
     * Notify users via HipChat.
     *
     * @param params Mandatory parameters of this notification. "event", "apiToken" and "room" are expected.
     */
    void hipchat(Map<String, Object> params,
                 @DelegatesTo(value = HipChatNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        hipchat(params['event'] as NotificationEvent,
                params['apiToken'] as String, params['room'] as String,
                closure)
    }

    /**
     * Notify users via HipChat.
     *
     * @param event The event when this notification should be raised
     * @param apiToken Your Hipchat API token. You need a user level token. Generate one at
     * https://yourdomain.hipchat.com/group_admin/api.
     * @param room Name of the Hipchat room (or ID)
     *
     * @deprecated use {@link #hipchat(Map, Closure)} instead
     */
    @Deprecated
    void hipchat(NotificationEvent event, String apiToken, String room,
                 @DelegatesTo(value = HipChatNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new HipChatNotification(event, apiToken, room, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify users via HipChat.
     *
     * @deprecated use {@link #hipchat(Map, Closure)} instead
     */
    @Deprecated
    void hipchat(NotificationEvent event,
                 @DelegatesTo(value = HipChatNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new HipChatNotification(event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * This notification type is obsolete and should no longer be used. Bamboo notifies Bitbucket Server about
     * every build result automatically.
     *
     * @deprecated use {@link #stashLegacy(Map, Closure)} instead
     */
    @Deprecated
    void stashLegacy(NotificationEvent event,
                     @DelegatesTo(value = StashLegacyNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new StashLegacyNotification(event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * This notification type is obsolete and should no longer be used. Bamboo notifies Bitbucket Server about
     * every build result automatically.
     */
    void stashLegacy(Map<String, Object> params,
              @DelegatesTo(value = UserNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        stashLegacy(params['event'] as NotificationEvent, closure)
    }

    /**
     * Notify a group.
     *
     * @deprecated use {@link #group(Map, Closure)} instead
     */
    @Deprecated
    void group(NotificationEvent event,
               @DelegatesTo(value = GroupNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new GroupNotification(event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify a group.
     */
    void group(Map<String, Object> params,
               @DelegatesTo(value = UserNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        group(params['event'] as NotificationEvent, closure)
    }

    /**
     * Notify users via e-mail.
     *
     * @deprecated use {@link #email(Map, Closure)} instead
     */
    @Deprecated
    void email(NotificationEvent event,
               @DelegatesTo(value = EmailNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new EmailNotification(event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify users via e-mail.
     *
     * @param params Mandatory parameters of this notification. "event" is expected.
     */
    void email(Map<String, Object> params,
               @DelegatesTo(value = EmailNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        email(params['event'] as NotificationEvent, closure)
    }

    /**
     * Notify via instant messenger.
     *
     * @deprecated use {@link #imAddress(Map, Closure)} instead
     */
    @Deprecated
    void imAddress(NotificationEvent event,
                   @DelegatesTo(value = ImAddressNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new ImAddressNotification(event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify via instant messenger.
     */
    void imAddress(Map<String, Object> params,
                   @DelegatesTo(value = EmailNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        imAddress(params['event'] as NotificationEvent, closure)
    }

    /**
     * Notify responsible users.
     *
     * @deprecated use {@link #responsibleUsers(Map, Closure)} instead
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
     *
     * @param params The mandatory parameters of this notification. "event" and "pluginKey" are expected.
     */
    void custom(Map<String, Object> params,
                @DelegatesTo(value = CustomNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        custom(params['event'] as NotificationEvent, params['pluginKey'] as String, closure)
    }

    /**
     * A custom (i.e., not built-in) notification.
     *
     * @deprecated use {@link #custom(Map, Closure)} instead
     */
    @Deprecated
    void custom(NotificationEvent event,
                String pluginKey,
                @DelegatesTo(value = CustomNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new CustomNotification(pluginKey, event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
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
