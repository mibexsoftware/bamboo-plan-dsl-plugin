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
            remote("mycommit") {
                repositories "test2"
                ipAddresses "127.0.0.1", "192.168.0.1"
                onlyRunIfOtherPlansArePassing {
                    planKeys "PROJ-PLAN1", "PROJ-PLAN3", "PROJ-PLAN5"
                }
            }
        }
    }
}