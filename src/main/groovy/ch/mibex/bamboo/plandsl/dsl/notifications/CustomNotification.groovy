package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationConditions

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class CustomNotification extends NotificationType {
    private Map<String, String[]> config = [:]

    CustomNotification(String pluginKey, NotificationConditions notificationConditions, BambooFacade bambooFacade) {
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
