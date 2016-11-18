package dsls.stages

project("SIMPLEPROJECT") {
    name "Renamed project"

    plan("SIMPLEPLAN") {
        name "Renamed plan"
        description "this was a simple plan"
        enabled false

        scm {
            git("myrepo") {
                url "http://localhost:7990/bitbucket/scm/project_1/java-maven-simple.git"
                branch "master"
                passwordAuth {
                    userName "admin"
                    password "admin"
                }
            }
        }

        stage("first stage") {
            description "this was a simple stage"
            manual true

            job(key = "SIMPLEJOB") {
                name "Simple job"
                description "This is a simple job"
                enabled

                tasks {
                    script('echo hello world') {

                    }
                }
            }
        }

        stage("second stage") {
            description "this was a simple stage"
            manual
        }
    }
}