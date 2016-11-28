package dsls.notifications.commons

import ch.mibex.bamboo.plandsl.dsl.BambooEnvironment
import ch.mibex.bamboo.plandsl.dsl.notifications.Notifications

static void notifyWithHipChatAndSms(Notifications notifications, BambooEnvironment env) {
    notifications.with {
        custom(NotificationEvent.JOB_ERROR,
                "ch.mibex.bamboo.smsnotification:smsnotification.recipient") {
            numberOfFailures 1
            configure(
                    twilioAccountSid: env('twilio_account_sid_password'),
                    twilioAuthToken: env('twilio_auth_token_password'),
                    smsFromNumber: env('sms_from_number'),
                    smsToNumber: env('sms_to_number')
            )
        }
        hipchat(NotificationEvent.ALL_BUILDS_COMPLETED) {
            apiToken env('hipchat_api_token_password')
            room env('hipchat_room_name')
            notify true
        }
    }
}