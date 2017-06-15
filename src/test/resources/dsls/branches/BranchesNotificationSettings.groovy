package dsls.branches

import ch.mibex.bamboo.plandsl.dsl.branches.Branch

project("SIMPLEPROJECT2") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        branches {
            notifications(Branch.NotifyOnNewBranchesType.DO_NOT_SEND_NOTIFICATIONS)
        }
    }
}