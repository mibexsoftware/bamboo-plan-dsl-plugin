package dsls.branches

project("SIMPLEPROJECT2") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        branches {
            triggers(NewPlanBranchesTriggerType.RUN_NEW_PLAN_BRANCHES_MANUALLY)
        }
    }
}