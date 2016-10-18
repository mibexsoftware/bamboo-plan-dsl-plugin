package dsls.tasks

project("SIMPLEPROJECT") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "simple plan"
        stage("simple stage") {
            description "this is a simple stage"
            manual false

            job("SIMPLEJOB") {
                name "Simple job"
                description "This is a simple job"
                enabled true

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
            }

        }
    }
}