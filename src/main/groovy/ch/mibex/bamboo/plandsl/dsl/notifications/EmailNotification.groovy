package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class EmailNotification extends NotificationType {
    private static final NOTIFICATION_RECIPIENT_TYPE =
            'com.atlassian.bamboo.plugin.system.notifications:recipient.address'
    private String address

    EmailNotification() {
        super(NOTIFICATION_RECIPIENT_TYPE)
    }

    EmailNotification(Notifications.NotificationEvent event, BambooFacade bambooFacade) {
        super(NOTIFICATION_RECIPIENT_TYPE, event, bambooFacade)
    }

    /**
     * Email address (e.g., bob@work.com)
     */
    void email(String email) {
        this.address = email
    }

    @Override
    protected Map<String, String[]> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('notificationEmailString', [address] as String[])
        config
    }

}
