package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
abstract class AbstractNotifications extends BambooObject {
    protected List<NotificationType> notifications = []

    protected AbstractNotifications() {}

    protected AbstractNotifications(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Notify users via HipChat.
     *
     * @deprecated use {@link #hipchat(Map, Closure)} instead
     */
    @Deprecated
    void hipchat(Notifications.NotificationEvent event,
                 @DelegatesTo(value = HipChatNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new HipChatNotification(event, bambooFacade)
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
        hipchat(translateEventNotifications(params['event']),
                params['apiToken'] as String, params['room'] as String,
                closure)
    }

    /**
     * Notify users via HipChat.
     *
     * @param event The event when this notification should be raised
     * @param apiToken Your Hipchat API token. You need a user level token. Generate one at
     * https://yourdomain.hipchat.com/group_admin/api.
     * @param room Name of the Hipchat room (or ID)
     *
     * @deprecated use {@link #hipchat(Map, Closure)} instead
     */
    @Deprecated
    void hipchat(Notifications.NotificationEvent event, String apiToken, String room,
                 @DelegatesTo(value = HipChatNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new HipChatNotification(event, apiToken, room, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify a group.
     */
    void group(Notifications.NotificationEvent event,
               @DelegatesTo(value = GroupNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new GroupNotification(event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify a user.
     */
    void user(Notifications.NotificationEvent event,
              @DelegatesTo(value = UserNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new UserNotification(event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify via instant messenger.
     */
    void imAddress(Notifications.NotificationEvent event,
                   @DelegatesTo(value = ImAddressNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new ImAddressNotification(event, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify users via e-mail.
     *
     * @deprecated use {@link #email(Map, Closure)} instead
     */
    @Deprecated
    void email(Notifications.NotificationEvent event,
               @DelegatesTo(value = EmailNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def notification = new EmailNotification(event, bambooFacade)
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
        email(translateEventNotifications(params['event']), closure)
    }

    @SuppressWarnings('Instanceof')
    static Notifications.NotificationEvent translateEventNotifications(Object obj) {
        if (obj instanceof EnvironmentNotifications.EnvironmentNotificationEvent) {
            switch (obj) {
                case EnvironmentNotifications.EnvironmentNotificationEvent.DEPLOYMENT_STARTED_AND_FINISHED:
                    Notifications.NotificationEvent.DEPLOYMENT_STARTED_AND_FINISHED
                    break
                case EnvironmentNotifications.EnvironmentNotificationEvent.DEPLOYMENT_FINISHED:
                    Notifications.NotificationEvent.DEPLOYMENT_FINISHED
                    break
                case EnvironmentNotifications.EnvironmentNotificationEvent.DEPLOYMENT_FAILED:
                    Notifications.NotificationEvent.DEPLOYMENT_FAILED
                    break
                default: throw new IllegalArgumentException("Unknown event type ${obj.class.name}")
            }
        } else {
            obj as Notifications.NotificationEvent
        }
    }

}
