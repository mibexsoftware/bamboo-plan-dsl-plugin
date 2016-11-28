package dsls.branches

project("SIMPLEPROJECT2") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        branches {
            notifications {
                custom(NotificationEvent.AFTER_X_BUILD_FAILURES, "") {}
            }
        }
    }
}