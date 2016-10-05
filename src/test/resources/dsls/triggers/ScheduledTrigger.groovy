package dsls.triggers

project("SIMPLEPROJECT") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        triggers {
            scheduled("scheduled") {
                cronExpression "0 0 0 ? * *"
                onlyRunIfOtherPlansArePassing {
                    planKeys "PROJ-PLAN2"
                }
            }
        }
    }
}