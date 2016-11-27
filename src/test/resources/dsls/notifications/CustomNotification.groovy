package dsls.notifications

project("SIMPLEPROJECT") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        notifications {
            custom(NotificationConditions.AFTER_X_BUILD_FAILURES,
                   "ch.mibex.bamboo.smsnotification:smsnotification.recipient") {
                numberOfFailures 1
                configure(
                        twilioAccountSid: "twilioAccountSid",
                        twilioAuthToken: "twilioAuthToken",
                        smsFromNumber: "smsFromNumber",
                        smsToNumber: "smsToNumber"
                )
            }
            hipchat(NotificationConditions.ALL_BUILDS_COMPLETED) {
                apiToken "XXX"
                room "Myroom"
                notify true
            }
        }
    }
}