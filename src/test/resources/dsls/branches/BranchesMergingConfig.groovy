package dsls.branches

project("SIMPLEPROJECT2") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        branches {
            merging {
                gateKeeper {
                    checkoutBranch "SIMPLEPROJECT-SIMPLEPLAN"
                    pushEnabled true
                }
            }
        }
    }
}