package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade

import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationConditions

class EmailNotification extends NotificationType {
    private static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.plugin.system.notifications:recipient.email'
    private String email

    EmailNotification(NotificationConditions conditionKey, BambooFacade bambooFacade) {
        super(NOTIFICATION_RECIPIENT_TYPE, conditionKey, bambooFacade)
    }

    /**
     * Email address (e.g., bob@work.com)
     */
    void email(String email) {
        this.email = email
    }

    @Override
    protected Map<String, String[]> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('notificationEmailString', [email] as String[])
        config
    }

}
