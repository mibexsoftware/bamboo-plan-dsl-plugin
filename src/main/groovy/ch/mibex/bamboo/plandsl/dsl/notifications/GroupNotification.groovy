package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade

import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationConditions

class GroupNotification extends NotificationType {
    private static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.plugin.system.notifications:recipient.group'
    private String group

    GroupNotification(NotificationConditions conditionKey, BambooFacade bambooFacade) {
        super(NOTIFICATION_RECIPIENT_TYPE, conditionKey, bambooFacade)
    }

    /**
     * Group name of the group to notify.
     */
    void group(String group) {
        this.group = group
    }

    @Override
   protected Map<String, String[]> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('notificationGroupString', [group] as String[])
        config
    }

}
