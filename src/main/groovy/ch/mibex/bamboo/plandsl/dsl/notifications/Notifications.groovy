package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ["metaClass"])
@ToString(includeFields=true)
class Notifications extends BambooObject {
    private List<NotificationType> notifications = []

    protected Notifications() {}

    Notifications(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Notify users via HipChat.
     */
    void hipchat(NotificationConditions conditions, @DelegatesTo(HipChatNotification) Closure closure) {
        def notification = new HipChatNotification(conditions, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify users via e-mail.
     */
    void email(NotificationConditions conditions, @DelegatesTo(EmailNotification) Closure closure) {
        def notification = new EmailNotification(conditions, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * This notification type is obsolete and should no longer be used. Bamboo notifies Bitbucket Server about
     * every build result automatically.
     */
    @Deprecated
    void stashLegacy(NotificationConditions conditions, @DelegatesTo(StashLegacyNotification) Closure closure) {
        def notification = new StashLegacyNotification(conditions, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify a group.
     */
    void group(NotificationConditions conditions, @DelegatesTo(GroupNotification) Closure closure) {
        def notification = new GroupNotification(conditions, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify a user.
     */
    void user(NotificationConditions conditions, @DelegatesTo(UserNotification) Closure closure) {
        def notification = new UserNotification(conditions, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify via instant messenger.
     */
    void imAddress(NotificationConditions conditions, @DelegatesTo(ImAddressNotification) Closure closure) {
        def notification = new ImAddressNotification(conditions, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify responsible users.
     */
    void responsibleUsers(NotificationConditions conditions,
                          @DelegatesTo(ResponsibleUsersNotification) Closure closure) {
        def notification = new ResponsibleUsersNotification(conditions, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify users who have committed to the build.
     */
    void committers(NotificationConditions conditions, @DelegatesTo(CommittersNotification) Closure closure) {
        def notification = new CommittersNotification(conditions, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify users who have marked this build as their favourite.
     */
    void watchers(NotificationConditions conditions, @DelegatesTo(WatchersNotification) Closure closure) {
        def notification = new WatchersNotification(conditions, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * A custom (i.e., not built-in) notification.
     */
    void custom(NotificationConditions conditions,
                String pluginKey,
                @DelegatesTo(CustomNotification) Closure closure) {
        def notification = new CustomNotification(pluginKey, conditions, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    static enum NotificationConditions {
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
        JOB_QUEUE_WITHOUT_CAPABLE_AGENTS('com.atlassian.bamboo.plugin.system.notifications:buildMissingCapableAgent')

        String bambooNotificationConditionKey

        NotificationConditions(String bambooNotificationConditionKey) {
            this.bambooNotificationConditionKey = bambooNotificationConditionKey
        }
    }

}
