package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class UserNotification extends NotificationType {
    static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.plugin.system.notifications:recipient.user'
    String user

    UserNotification(String conditionKey, BambooFacade bambooFacade) {
        super(NOTIFICATION_RECIPIENT_TYPE, conditionKey, bambooFacade)
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
