package ch.mibex.bamboo.plandsl.dsl.notifications

import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationConditions

class ResponsibleUsersNotification extends NotificationType {
    static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.brokenbuildtracker:recipient.responsible'

    ResponsibleUsersNotification(NotificationConditions conditionKey) {
        super(NOTIFICATION_RECIPIENT_TYPE, conditionKey)
    }

    @Override
    Map<String, String[]> getConfig(Map<Object, Object> context) {
        [:]
    }

}
