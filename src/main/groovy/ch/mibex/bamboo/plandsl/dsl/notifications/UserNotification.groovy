package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class UserNotification extends NotificationType {
    static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.plugin.system.notifications:recipient.user'
    private String user

    // just for testing:
    protected UserNotification() {
        super(NOTIFICATION_RECIPIENT_TYPE)
    }

    UserNotification(Notifications.NotificationEvent event, BambooFacade bambooFacade) {
        super(NOTIFICATION_RECIPIENT_TYPE, event, bambooFacade)
    }

    /**
     * User to receive notification.
     */
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
