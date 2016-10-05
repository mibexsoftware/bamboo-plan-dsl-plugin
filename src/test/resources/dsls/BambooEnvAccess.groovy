package dsls


project("SIMPLEPROJECT") {
    name "Renamed project"

    plan("SIMPLEPLAN") {
        name "Renamed plan"
        description "this was a simple plan"
        enabled false

        notifications {
            hipchat(NotificationConditions.ALL_BUILDS_COMPLETED) {
                apiToken ""
                room bamboo['my.key']
                notify true
            }
        }
    }
}