package dsls.deployprojs

project("SIMPLEPROJECT") {
    name "simple project"

    plan("SIMPLEPLAN") {
        name "simple plan"

        deploymentProject("dp1") {
            description "desc1"
            useCustomPlanBranch "develop"

            environment("env1") {
                description "desc"
            }

            releaseVersioning(nextReleaseVersion: '1.0-m1') {
                autoIncrement()
                variables "test1", "test2"
            }
        }

        deploymentProject(name: "dp2") {
            description "desc1"
            useCustomPlanBranch "release"

            environment(name: "env1") {
                description "desc1"
            }
            environment("env2") {
                description "desc2"
            }

            releaseVersioning('1.0-${bamboo.buildNumber}') {
                variables "test3", "test4"
            }
        }

    }
}