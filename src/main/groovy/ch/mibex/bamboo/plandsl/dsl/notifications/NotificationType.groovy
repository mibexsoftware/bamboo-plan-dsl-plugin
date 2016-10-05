package ch.mibex.bamboo.plandsl.dsl.notifications

import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationConditions

abstract class NotificationType {
    NotificationConditions conditionKey
    String notificationRecipientType
    int numberOfFailures

    protected NotificationType(String notificationRecipientType, NotificationConditions conditionKey) {
        this.notificationRecipientType = notificationRecipientType
        this.conditionKey = conditionKey
    }

    def Map<String, String[]> getNotificationConfig(Map<Object, Object> context) {
        def config = getConfig(context)
        if (numberOfFailures > 0) {
            config.put('numFailedTimes', [numberOfFailures] as String[])
        }
        config
    }

    protected abstract def Map<String, String[]> getConfig(Map<Object, Object> context)

    void numberOfFailures(int num) {
        this.numberOfFailures = num
    }

}
