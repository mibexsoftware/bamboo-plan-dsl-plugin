package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class ResponsibleUsersNotification extends NotificationType {
    private static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.brokenbuildtracker:recipient.responsible'

    ResponsibleUsersNotification(Notifications.NotificationEvent event, BambooFacade bambooFacade) {
        super(NOTIFICATION_RECIPIENT_TYPE, event, bambooFacade)
    }

    @Override
    protected Map<String, String[]> getConfig(Map<Object, Object> context) {
        [:]
    }

}
