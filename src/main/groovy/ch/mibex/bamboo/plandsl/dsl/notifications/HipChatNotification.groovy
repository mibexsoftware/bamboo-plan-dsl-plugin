package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade

import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationConditions

class HipChatNotification extends NotificationType {
    private static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.plugins.bamboo-hipchat:recipient.hipchat'
    private String apiToken
    private String room
    private boolean notify

    HipChatNotification(NotificationConditions conditionKey, BambooFacade bambooFacade) {
        super(NOTIFICATION_RECIPIENT_TYPE, conditionKey, bambooFacade)
    }

    /**
     * Your Hipchat API token. You need a user level token. Generate one at
     * https://yourdomain.hipchat.com/group_admin/api.
     */
    void apiToken(String apiToken) {
        this.apiToken = apiToken
    }

    /**
     * Name of the Hipchat room (or ID)
     */
    void room(String room) {
        this.room = room
    }

    /**
     * @deprecated use #notifyParticipants(boolean notify) instead
     */
    @Deprecated
    void notify(boolean notify) {
        this.notify = notify
    }

    /**
     * Enable this to notify room participants (i.e., via Growl) of the new message.
     */
    void notifyParticipants(boolean notify = true) {
        this.notify = notify
    }

    @Override
    protected Map<String, String[]> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('apiToken', [apiToken] as String[])
        config.put('room', [room] as String[])
        config.put('notifyUsers', ['' + notify] as String[])
        config
    }

}
