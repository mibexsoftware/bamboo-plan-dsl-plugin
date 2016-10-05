package dsls.tasks

project("SIMPLEPROJECT") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        stage("simple stage") {
            description "this is a simple stage"
            manual false

            job("SIMPLEJOB") {
                name "Simple job"
                description "This is a simple job"
                enabled true

                tasks {
                    script("say hello world") {
                        enabled true
                        workingSubDirectory "."
                        argument "-n"
                        environmentVariables "what=EVER"
                        inline {
                            scriptBody 'echo "Hello world"'
                            interpreter ScriptInterpreter.LEGACY_SH_BAT
                        }
                    }
                }
            }

        }
    }
}