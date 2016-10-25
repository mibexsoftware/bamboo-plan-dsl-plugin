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
                    deployPlugin("Deploy plug-in to staging server") {
                        enabled true
                        productType ProductType.STASH
                        deployUsername "admin"
                        deployArtifactName "Plan DSL"
                        deployURL "http://myserver"
                        deployPasswordVariable '${bamboo.bitbucket_server_password}'
                        deployBranchEnabled true
                        certificateCheckDisabled true
                    }
                }
            }
        }
    }
}