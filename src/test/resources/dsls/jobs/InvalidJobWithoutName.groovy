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

            job("SIMPLEJOB") {
                name null
                description "This was a simple job"
                enabled false

                tasks {
                    script('echo hello world') {
                    }
                }
            }

        }
    }
}