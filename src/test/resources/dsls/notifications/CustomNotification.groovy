package dsls.notifications

project("SIMPLEPROJECT") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        notifications {
            custom(event: NotificationEvent.AFTER_X_BUILD_FAILURES,
                   pluginKey: "ch.mibex.bamboo.smsnotification:smsnotification.recipient") {
                numberOfFailures 1
                configure(
                        twilioAccountSid: "twilioAccountSid",
                        twilioAuthToken: "twilioAuthToken",
                        smsFromNumber: "smsFromNumber",
                        smsToNumber: "smsToNumber"
                )
            }
            hipchat(event: NotificationEvent.ALL_BUILDS_COMPLETED, apiToken: "XXX", room: "MyRoom") {
                notify true
            }
            email(event: NotificationEvent.FIRST_FAILED_JOB_FOR_PLAN) {
                email 'your@mail.com'
            }
        }
    }
}