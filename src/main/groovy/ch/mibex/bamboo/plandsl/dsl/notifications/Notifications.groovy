package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.DslParentElement
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class Notifications implements DslParentElement<NotificationType> {
    Set<NotificationType> notifications = new LinkedHashSet<>()

    void hipchat(NotificationConditions conditions, @DelegatesTo(HipChatNotification) Closure closure) {
        def notification = new HipChatNotification(conditions)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    void email(NotificationConditions conditions, @DelegatesTo(EmailNotification) Closure closure) {
        def notification = new EmailNotification(conditions)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    void stashLegacy(NotificationConditions conditions, @DelegatesTo(StashLegacyNotification) Closure closure) {
        def notification = new StashLegacyNotification(conditions)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    void group(NotificationConditions conditions, @DelegatesTo(GroupNotification) Closure closure) {
        def notification = new GroupNotification(conditions)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    void user(NotificationConditions conditions, @DelegatesTo(UserNotification) Closure closure) {
        def notification = new UserNotification(conditions)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    void imAddress(NotificationConditions conditions, @DelegatesTo(ImAddressNotification) Closure closure) {
        def notification = new ImAddressNotification(conditions)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    void responsibleUsers(NotificationConditions conditions,
                          @DelegatesTo(ResponsibleUsersNotification) Closure closure) {
        def notification = new ResponsibleUsersNotification(conditions)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    void committers(NotificationConditions conditions, @DelegatesTo(CommittersNotification) Closure closure) {
        def notification = new CommittersNotification(conditions)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    void watchers(NotificationConditions conditions, @DelegatesTo(WatchersNotification) Closure closure) {
        def notification = new WatchersNotification(conditions)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    void custom(NotificationConditions conditions,
                String pluginKey,
                @DelegatesTo(CustomNotification) Closure closure) {
        def notification = new CustomNotification(pluginKey, conditions)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    @Override
    Collection<NotificationType> children() {
        notifications
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
