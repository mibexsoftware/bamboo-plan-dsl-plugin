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
                    deployPlugin(
                            productType: ch.mibex.bamboo.plandsl.dsl.tasks.DeployPluginTask.ProductType.STASH,
                            deployUsername: "admin",
                            deployURL: "http://myserver",
                            deployArtifactName: "Plan DSL",
                            deployPasswordVariable: '${bamboo.bitbucket_server_password}'
                    ) {
                        description "Deploy plug-in to staging server"
                        enabled true
                        deployBranchEnabled true
                        certificateCheckDisabled true
                    }
                }
            }
        }
    }
}