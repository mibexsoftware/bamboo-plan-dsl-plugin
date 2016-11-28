package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationEvent

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class WatchersNotification extends NotificationType {
    private static final NOTIFICATION_RECIPIENT_TYPE =
            'com.atlassian.bamboo.plugin.system.notifications:recipient.watcher'

    WatchersNotification(NotificationEvent conditionKey, BambooFacade bambooFacade) {
        super(NOTIFICATION_RECIPIENT_TYPE, conditionKey, bambooFacade)
    }

    @Override
    protected Map<String, String[]> getConfig(Map<Object, Object> context) {
        [:]
    }

}
