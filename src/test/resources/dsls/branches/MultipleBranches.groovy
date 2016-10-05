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
            branch("release") {
                description "my release branch"
                enabled true
                cleanupAutomatically true
            }
            branch("test") {
                description "my test branch"
                enabled true
                cleanupAutomatically true
            }
        }
    }
}