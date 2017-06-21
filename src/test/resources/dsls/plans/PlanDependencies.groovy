package dsls.plans

project("SIMPLEPROJECT") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        dependencies {
            advancedOptions {
                triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully true
                autoDependencyManagement true
                enableDependenciesForAllBranches true
            }

            blockingStrategy DependencyBlockingStrategy.BLOCK_BUILD_IF_PARENT_BUILDS_ARE_QUEUED

            childPlans "HELLO-HELLO", "SEED-SEED"
        }
    }
}