package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class StashLegacyNotification extends NotificationType {
    static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.plugins.bamboo-stash-plugin:recipient.stash'

    // just for testing
    protected StashLegacyNotification() {
        super(NOTIFICATION_RECIPIENT_TYPE)
    }

    // single-arg ctor necessary for !stashLegacy
    protected StashLegacyNotification(String e) {
        super(NOTIFICATION_RECIPIENT_TYPE)
    }

    StashLegacyNotification(Notifications.NotificationEvent event, BambooFacade bambooFacade) {
        super(NOTIFICATION_RECIPIENT_TYPE, event, bambooFacade)
    }

    @Override
    protected Map<String, String[]> getConfig(Map<Object, Object> context) {
        [:]
    }

}
