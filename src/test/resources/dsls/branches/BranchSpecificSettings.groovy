package dsls.branches

project("SIMPLEPROJECT2") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        branches {
            branch("develop") {
                description "my developer branch"
                enabled true
                cleanupAutomatically true
            }
        }
    }
}