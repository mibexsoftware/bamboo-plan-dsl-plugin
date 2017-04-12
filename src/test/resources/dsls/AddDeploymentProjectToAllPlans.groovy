package dsls

[['ACTIVITIES', 'Activity Streams for Bitbucket Server'], ['PLANDSL', 'Plan DSL for Bamboo']].each { planInfos ->
    project("ATLAS") {
        name 'plan'
        plan(planInfos[0]) {
            name 'plan'
            deploymentProject(planInfos[1]) {
                description "Deployment project for plug-in"
                environment("Integration") {
                    description "Deploy plug-in to integration server"

                    tasks {
                        cleanWorkingDirectory() {
                            description "Clean the working directory"
                        }

                        artifactDownload() {
                            description "Download release contents"
                            artifact(name: "plug-in") {
                            }
                        }
                    }
                }
            }
        }
    }
}

