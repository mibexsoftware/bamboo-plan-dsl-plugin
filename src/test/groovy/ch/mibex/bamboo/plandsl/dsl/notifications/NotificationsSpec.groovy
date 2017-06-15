package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class NotificationsSpec extends Specification {

    def 'external notifications'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/notifications/ExternalNotifications.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))
        def customNotification = results.projects[0].plans[0].notifications.notifications[0]
        def hipchatNotification = results.projects[0].plans[0].notifications.notifications[1]

        then:
        customNotification == new CustomNotification(
                notificationRecipientType: "ch.mibex.bamboo.smsnotification:smsnotification.recipient",
                event: Notifications.NotificationEvent.JOB_ERROR,
                numberOfFailures: 1,
                config: [
                        twilioAccountSid: "twilio_account_sid_password".split(),
                        twilioAuthToken: "twilio_auth_token_password".split(),
                        smsFromNumber: "sms_from_number".split(),
                        smsToNumber: "sms_to_number".split()
                ]
        )
        hipchatNotification == new HipChatNotification(
                notificationRecipientType: "com.atlassian.bamboo.plugins.bamboo-hipchat:recipient.hipchat",
                event:  Notifications.NotificationEvent.ALL_BUILDS_COMPLETED,
                apiToken: 'hipchat_api_token_password',
                room: 'hipchat_room_name',
                notify: true
        )
    }
}