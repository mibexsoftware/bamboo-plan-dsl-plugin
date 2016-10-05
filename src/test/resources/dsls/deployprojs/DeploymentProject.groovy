package dsls.deployprojs

project("SIMPLEPROJECT") {
    plan("SIMPLEPLAN") {

        deploymentProject("dp1") {
            description "desc1"
            useCustomPlanBranch "develop"

            environment("env1") {
                description "desc"
            }
        }

        deploymentProject("dp2") {
            description "desc1"
            useCustomPlanBranch "release"

            environment("env1") {
                description "desc1"
            }
            environment("env2") {
                description "desc2"
            }
        }

    }
}