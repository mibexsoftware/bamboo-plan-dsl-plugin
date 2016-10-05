package ch.mibex.bamboo.plandsl.dsl.notifications

import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationConditions

class ImAddressNotification extends NotificationType {
    static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.plugin.system.notifications:recipient.im'
    String instantMessagingAddress

    ImAddressNotification(NotificationConditions conditionKey) {
        super(NOTIFICATION_RECIPIENT_TYPE, conditionKey)
    }

    void instantMessagingAddress(String instantMessagingAddress) {
        this.instantMessagingAddress = instantMessagingAddress
    }

    @Override
    Map<String, String[]> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('notificationIMString', [instantMessagingAddress] as String[])
        config
    }

}
