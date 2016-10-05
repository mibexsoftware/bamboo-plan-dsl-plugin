package dsls.jobs

project("SIMPLEPROJECT") {
    name "Renamed project"

    plan("SIMPLEPLAN") {
        name "Renamed plan"
        description "this was a simple plan"
        enabled false

        stage("simple stage") {
            description "this is simple stage"
            manual false

            job("SIMPLEJOB1") {
                name "Simple job 1"
                description "This was a simple job"
                enabled false

                tasks {
                    script('echo hello world') {
                    }
                }

                artifacts {
                    definition("my JAR") {
                        location "target"
                        copyPattern "**/*.jar"
                        shared true
                    }
                    definition("my ZIP") {
                        location "target"
                        copyPattern "**/*.zip"
                        shared true
                    }
                }
            }

        }
    }
}
