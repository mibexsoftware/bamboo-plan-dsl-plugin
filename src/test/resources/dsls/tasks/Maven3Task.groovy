package dsls.tasks

project("SIMPLEPROJECT") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"

        stage("simple stage") {
            description "this is a simple stage"
            manual false

            job("SIMPLEJOB") {
                name "Simple job"
                description "This is a simple job"
                enabled true

                tasks {
                    maven3x(goal: 'install') {
                        description 'build plug-in'
                        workingSubDirectory "."
                        executable "maven323"
                        buildJdk "jdk8"
                        environmentVariables "what=EVER"
                        withTests {
                            testResultsDirectory 'tests/'
                        }
                        useMavenReturnCode false
                        projectFile "a/b"
                    }
                }
            }

        }
    }
}