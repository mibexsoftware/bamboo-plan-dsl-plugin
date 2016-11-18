package ch.mibex.bamboo.plandsl.dsl.notifications

import static ch.mibex.bamboo.plandsl.dsl.notifications.Notifications.NotificationConditions

class HipChatNotification extends NotificationType {
    static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.plugins.bamboo-hipchat:recipient.hipchat'
    String apiToken
    String room
    boolean notify

    HipChatNotification(NotificationConditions conditionKey) {
        super(NOTIFICATION_RECIPIENT_TYPE, conditionKey)
    }

    void apiToken(String apiToken) {
        this.apiToken = apiToken
    }

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

    void notifyParticipants(boolean notify = true) {
        this.notify = notify
    }

    @Override
    Map<String, String[]> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('apiToken', [apiToken] as String[])
        config.put('room', [room] as String[])
        config.put('notifyUsers', ['' + notify] as String[])
        config
    }

}
