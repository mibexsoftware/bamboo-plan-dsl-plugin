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
                    maven3("build plug-in") {
                        goal "install"
                        enabled true
                        isFinal false
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