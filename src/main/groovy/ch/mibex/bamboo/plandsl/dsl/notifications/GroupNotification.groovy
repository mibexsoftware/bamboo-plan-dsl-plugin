package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class GroupNotification extends NotificationType {
    private static final NOTIFICATION_RECIPIENT_TYPE =
            'com.atlassian.bamboo.plugin.system.notifications:recipient.group'
    private String group

    // just for testing:
    protected GroupNotification() {
    }

    GroupNotification(Notifications.NotificationEvent event, BambooFacade bambooFacade) {
        super(NOTIFICATION_RECIPIENT_TYPE, event, bambooFacade)
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
