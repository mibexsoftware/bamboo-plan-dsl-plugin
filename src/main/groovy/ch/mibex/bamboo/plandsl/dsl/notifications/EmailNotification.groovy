package ch.mibex.bamboo.plandsl.dsl.notifications

import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationConditions

class EmailNotification extends NotificationType {
    static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.plugin.system.notifications:recipient.email'
    String email

    EmailNotification(NotificationConditions conditionKey) {
        super(NOTIFICATION_RECIPIENT_TYPE, conditionKey)
    }

    void email(String email) {
        this.email = email
    }

    @Override
    Map<String, String[]> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('notificationEmailString', [email] as String[])
        config
    }

}
