package ch.mibex.bamboo.plandsl.dsl.notifications

import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationConditions

class CommittersNotification extends NotificationType {
    static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.plugin.system.notifications:recipient.committer'

    CommittersNotification(NotificationConditions conditionKey) {
        super(NOTIFICATION_RECIPIENT_TYPE, conditionKey)
    }

    @Override
    Map<String, String[]> getConfig(Map<Object, Object> context) {
        [:]
    }

}
