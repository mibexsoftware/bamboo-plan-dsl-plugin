package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.NullBambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
abstract class NotificationType extends BambooObject {
    protected Notifications.NotificationEvent event
    protected String notificationRecipientType
    protected int numberOfFailures

    protected NotificationType(String notificationRecipientType,
                               Notifications.NotificationEvent event,
                               BambooFacade bambooFacade) {
        super(bambooFacade)
        this.notificationRecipientType = notificationRecipientType
        this.event = event
    }

    protected NotificationType(String notificationRecipientType) {
        this(notificationRecipientType, null, new NullBambooFacade())
    }

    protected NotificationType() {}

    Map<String, String[]> getNotificationConfig(Map<Object, Object> context) {
        def config = getConfig(context)
        if (numberOfFailures > 0) {
            config.put('numFailedTimes', [numberOfFailures] as String[])
        }
        config
    }

    protected abstract Map<String, String[]> getConfig(Map<Object, Object> context)

    void numberOfFailures(int num) {
        this.numberOfFailures = num
    }
}
