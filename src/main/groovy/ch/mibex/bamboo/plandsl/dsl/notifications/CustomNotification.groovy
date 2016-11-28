package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationEvent

@EqualsAndHashCode(includeFields = true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields = true)
class CustomNotification extends NotificationType {
    private Map<String, String[]> config = [:]

    // just for tests:
    protected CustomNotification() {}

    CustomNotification(String pluginKey, NotificationEvent notificationConditions, BambooFacade bambooFacade) {
        super(pluginKey, notificationConditions, bambooFacade)
    }

    def methodMissing(String methodName, args) {
        config << [(methodName): [args[0].toString()] as String[]]
    }

    def configure(Map<String, Object> config) {
        config.each { k, v -> this.config << [(k): [v.toString()] as String[]] }
    }

    @Override
    protected Map<String, String[]> getConfig(Map<Object, Object> context) {
        config
    }
}
