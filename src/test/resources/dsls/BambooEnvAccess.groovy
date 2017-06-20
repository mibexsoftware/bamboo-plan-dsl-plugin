package dsls


project(env('MYPROJECTKEY')) {
    name "Renamed project"

    plan("SIMPLEPLAN") {
        name "Renamed plan"
        description "this was a simple plan"
        enabled false

        notifications {
            hipchat(event: NotificationEvent.ALL_BUILDS_COMPLETED, apiToken: env('my.otherkey'), room: env('my.key')) {
                notifyParticipants true
            }
        }
    }
}