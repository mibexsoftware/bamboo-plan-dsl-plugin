package ch.mibex.bamboo.plandsl.dsl.notifications

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationConditions

@EqualsAndHashCode
@ToString

class CustomNotification extends NotificationType {
    Map<String, String[]> config = [:]

    CustomNotification(String pluginKey, NotificationConditions notificationConditions) {
        super(pluginKey, notificationConditions)
    }

    def methodMissing(String methodName, args) {
        config << [(methodName): [args[0].toString()] as String[]]
    }

    def configure(Map<String, Object> config) {
        config.each { k, v -> this.config << [(k): [v.toString()] as String[]] }
    }

    @Override
    Map<String, String[]> getConfig(Map<Object, Object> context) {
        config
    }
}
