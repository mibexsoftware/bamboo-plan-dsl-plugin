package dsls.branches

project("SIMPLEPROJECT2") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        branches {
            autoBranchManagement {
                newPlanBranchesForMatchingBranchNames("feature/*")
                deletePlanBranchesAfterDays(7)
                deleteInactivePlanBranchesAfterDays(14)
            }
        }
    }
}