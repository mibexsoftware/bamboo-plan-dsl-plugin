package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class EnvironmentNotifications extends AbstractNotifications {
    protected EnvironmentNotifications() {}

    EnvironmentNotifications(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * @deprecated use {@link Notifications.NotificationEvent)} instead
     */
    @Deprecated
    void hipchat(EnvironmentNotificationEvent event, String apiToken, String room,
                 @DelegatesTo(value = HipChatNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        hipchat(translateEventNotifications(event), apiToken, room, closure)
    }

    /**
     * @deprecated use {@link Notifications.NotificationEvent)} instead
     */
    @Deprecated
    void email(EnvironmentNotificationEvent event,
               @DelegatesTo(value = EmailNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        email(translateEventNotifications(event), closure)
    }

    /**
     * @deprecated use {@link Notifications.NotificationEvent)} instead
     */
    @Deprecated
    void group(EnvironmentNotificationEvent event,
               @DelegatesTo(value = GroupNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        group(translateEventNotifications(event), closure)
    }

    /**
     * @deprecated use {@link Notifications.NotificationEvent)} instead
     */
    @Deprecated
    void user(EnvironmentNotificationEvent event,
             @DelegatesTo(value = UserNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        user(translateEventNotifications(event), closure)
    }

    /**
     * @deprecated use {@link Notifications.NotificationEvent)} instead
     */
    @Deprecated
    void imAddress(EnvironmentNotificationEvent event,
                   @DelegatesTo(value = ImAddressNotification, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        imAddress(translateEventNotifications(event), closure)
    }

    /**
     * @deprecated use {@link Notifications.NotificationEvent} instead
     */
    @Deprecated
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
