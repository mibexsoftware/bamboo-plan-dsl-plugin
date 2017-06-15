package dsls.jobs

project("SIMPLEPROJECT") {
    name "Renamed project"

    plan("SIMPLEPLAN") {
        name "Renamed plan"

        stage("simple stage") {
            description "this is simple stage"
            manual false

            job("SIMPLEJOB1") {
                name "Simple job 1"
                description "This was a simple job"
                enabled false
            }
            job("SIMPLEJOB2") {
                name "Simple job 2"
                description "This was a simple job"
                enabled false
            }
            job("SIMPLEJOB3") {
                name "Simple job 3"
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
                }

                requirements {

                }

                miscellaneous {

                }
            }

        }
    }
}
