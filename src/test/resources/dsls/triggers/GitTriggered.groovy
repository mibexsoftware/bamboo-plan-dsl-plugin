package dsls.triggers

project("BITBUCKETSERVER") {
    name "Simple project"

    plan("BITBUCKETSERVER") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        triggers {
            polling("mypollper") {
                repositories "myGitRepo"
                periodically {
                    pollingFrequencyInSecs 10
                }
            }
        }

        stage("simple stage") {
            description "this is a simple stage"
            manual false

            job("SIMPLEJOB") {
                name "Simple job"
                description "This is a simple job"
                enabled true

                tasks {
                    checkout("checkout default repo") {
                        forceCleanBuild true

                        repository("myGitRepo") {
                            checkoutDirectory "a/b"
                        }

                    }
                }
            }
        }

        scm {
            git("myGitRepo") {
                url "http://localhost:7990/bitbucket/scm/project_1/rep_1.git"
                branch "master"
                passwordAuth {
                    userName "admin"
                    password "admin"
                }
            }
        }
    }
}