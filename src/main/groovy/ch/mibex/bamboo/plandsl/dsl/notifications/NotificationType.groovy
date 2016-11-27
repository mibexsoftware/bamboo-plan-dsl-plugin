package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject

import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationConditions

abstract class NotificationType extends BambooObject {
    protected NotificationConditions conditionKey
    protected String notificationRecipientType
    protected int numberOfFailures

    protected NotificationType(String notificationRecipientType,
                               NotificationConditions conditionKey,
                               BambooFacade bambooFacade) {
        super(bambooFacade)
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
