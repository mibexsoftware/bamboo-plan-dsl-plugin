package ch.mibex.bamboo.plandsl.dsl.notifications

import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationConditions

class UserNotification extends NotificationType {
    static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.plugin.system.notifications:recipient.user'
    String user

    UserNotification(NotificationConditions conditionKey) {
        super(NOTIFICATION_RECIPIENT_TYPE, conditionKey)
    }

    void user(String user) {
        this.user = user
    }

    @Override
    Map<String, String[]> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('notificationUserString', [user] as String[])
        config
    }

}
