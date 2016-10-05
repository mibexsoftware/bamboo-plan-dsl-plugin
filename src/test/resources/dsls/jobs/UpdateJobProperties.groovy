package dsls.jobs

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

        stage("simple stage") {
            description "this is simple stage"
            manual false

            job("SIMPLEJOB") {
                name "Renamed job"
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