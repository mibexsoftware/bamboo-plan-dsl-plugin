package dsls.branches

project("SIMPLEPROJECT2") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        branches {
            branch("develop") {
                triggers {
                    scheduled {
                        cronExpression "0 15 10 * * ?"
                        onlyRunIfOtherPlansArePassing {
                            planKeys "SEED-SEED-JOB1", "SEED-SEED-JOB2"
                        }
                    }
                }
            }
        }
    }
}