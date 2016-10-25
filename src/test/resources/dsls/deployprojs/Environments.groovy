package dsls.deployprojs

project("SIMPLEPROJECT") {
    name "simple project"

    plan("SIMPLEPLAN") {
        name "simple plan"

        deploymentProject("name") {
            description "desc"
            useCustomPlanBranch "develop"

            environment("env") {
                description "env desc"

                tasks {
                    command("run command") {
                        enabled true
                        isFinal true
                        workingSubDirectory "."
                        argument "-n"
                        environmentVariables "what=EVER"
                        executable "atlas-clean"
                    }
                }

                triggers {
                    scheduled("test") {
                        cronExpression "0 0 0 ? * *"
                    }
                }
            }
        }

    }
}