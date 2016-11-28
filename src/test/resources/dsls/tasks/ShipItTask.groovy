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
                    shipIt2marketplace(deployArtifactName: "Plan DSL") {
                        description "shipit to the Atlassian Marketplace"
                        enabled true
                        isFinal false
                        onlyAllowToTriggerFromJira true
                        runOnBranchBuilds false
                        publicVersion true
                        deduceBuildNrFromPluginVersion true
                        bambooUserId 'admin'
                        jqlToCollectReleaseNotes 'status in (resolved,closed,done)'
                    }
                }
            }
        }
    }
}