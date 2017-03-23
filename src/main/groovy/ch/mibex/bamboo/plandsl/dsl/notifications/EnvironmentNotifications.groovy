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
     * Notify users via HipChat.
     *
     * @deprecated use {@link #hipchat(Map, Closure)} instead
     */
    @Deprecated
    void hipchat(EnvironmentNotificationEvent event, @DelegatesTo(HipChatNotification) Closure closure) {
        def notification = new HipChatNotification(event.bambooNotificationConditionKey, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify users via HipChat.
     *
     * @param params Mandatory parameters of this notification. "event", "apiToken" and "room" are expected.
     */
    void hipchat(Map<String, Object> params, @DelegatesTo(HipChatNotification) Closure closure) {
        hipchat(params['event'] as EnvironmentNotificationEvent,
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
     */
    void hipchat(EnvironmentNotificationEvent event, String apiToken, String room,
                 @DelegatesTo(HipChatNotification) Closure closure) {
        def notification = new HipChatNotification(event.bambooNotificationConditionKey, apiToken, room, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify users via e-mail.
     */
    void email(EnvironmentNotificationEvent event, @DelegatesTo(EmailNotification) Closure closure) {
        def notification = new EmailNotification(event.bambooNotificationConditionKey, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify a group.
     */
    void group(EnvironmentNotificationEvent event, @DelegatesTo(GroupNotification) Closure closure) {
        def notification = new GroupNotification(event.bambooNotificationConditionKey, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify a user.
     */
    void user(EnvironmentNotificationEvent event, @DelegatesTo(UserNotification) Closure closure) {
        def notification = new UserNotification(event.bambooNotificationConditionKey, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    /**
     * Notify via instant messenger.
     */
    void imAddress(EnvironmentNotificationEvent event, @DelegatesTo(ImAddressNotification) Closure closure) {
        def notification = new ImAddressNotification(event.bambooNotificationConditionKey, bambooFacade)
        DslScriptHelper.execute(closure, notification)
        notifications << notification
    }

    static enum EnvironmentNotificationEvent {
        DEPLOYMENT_STARTED_AND_FINISHED('bamboo.deployments:deploymentStartedFinished'),
        DEPLOYMENT_FINISHED('bamboo.deployments:deploymentFinished'),
        DEPLOYMENT_FAILED('bamboo.deployments:deploymentFailed')

        String bambooNotificationConditionKey

        EnvironmentNotificationEvent(String bambooNotificationConditionKey) {
            this.bambooNotificationConditionKey = bambooNotificationConditionKey
        }
    }
}
