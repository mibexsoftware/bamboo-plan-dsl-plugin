package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class EnvironmentNotifications extends BambooObject {
    private List<NotificationType> notifications = []

    protected EnvironmentNotifications() {}

    EnvironmentNotifications(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Notify a user.
     */
    void user(Map<String, Object> params,
              @DelegatesTo(value = UserNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new UserNotification(params['event'] as Notifications.NotificationEvent, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify users via HipChat.
     *
     * @param params Mandatory parameters of this notification. "event", "apiToken" and "room" are expected.
     */
    void hipchat(Map<String, Object> params,
                 @DelegatesTo(value = HipChatNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new HipChatNotification(
            params['event'] as Notifications.NotificationEvent,
            params['apiToken'] as String,
            params['room'] as String,
            bambooFacade
        )
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify a group.
     */
    void group(Map<String, Object> params,
               @DelegatesTo(value = GroupNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new GroupNotification(params['event'] as Notifications.NotificationEvent, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify users via e-mail.
     *
     * @param params Mandatory parameters of this notification. "event" is expected.
     */
    void email(Map<String, Object> params,
               @DelegatesTo(value = EmailNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new EmailNotification(params['event'] as Notifications.NotificationEvent, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify via instant messenger.
     */
    void imAddress(Map<String, Object> params,
                   @DelegatesTo(value = EmailNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new ImAddressNotification(params['event'] as Notifications.NotificationEvent, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * A custom (i.e., not built-in) notification.
     *
     * @param params The mandatory parameters of this notification. "event" and "pluginKey" are expected.
     */
    void custom(Map<String, Object> params,
                @DelegatesTo(value = CustomNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new CustomNotification(
            params['pluginKey'] as String,
            params['event'] as Notifications.NotificationEvent,
            bambooFacade
        )
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }
}
