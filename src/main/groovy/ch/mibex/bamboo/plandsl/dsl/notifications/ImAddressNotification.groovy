package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class ImAddressNotification extends NotificationType {
    private static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.plugin.system.notifications:recipient.im'
    private String instantMessagingAddress

    // just for testing:
    protected ImAddressNotification() {
        super(NOTIFICATION_RECIPIENT_TYPE)
    }

    ImAddressNotification(Notifications.NotificationEvent event, BambooFacade bambooFacade) {
        super(NOTIFICATION_RECIPIENT_TYPE, event, bambooFacade)
    }

    /**
     * Instant messaging address (e.g., bill@chat.com)
     */
    void instantMessagingAddress(String instantMessagingAddress) {
        this.instantMessagingAddress = instantMessagingAddress
    }

    @Override
    protected Map<String, String[]> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('notificationIMString', [instantMessagingAddress] as String[])
        config
    }

}
