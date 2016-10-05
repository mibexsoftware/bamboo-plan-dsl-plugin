package dsls.triggers

project("SIMPLEPROJECT") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        scm {
            linkedRepository("test2")
        }

        triggers {
            polling("mypollper") {
                repositories "test2"
                periodically {
                    pollingFrequencyInSecs 180
                }
                onlyRunIfOtherPlansArePassing {
                    planKeys "PROJ-PLAN1", "PROJ-PLAN3", "PROJ-PLAN5"
                }
            }
        }
    }
}