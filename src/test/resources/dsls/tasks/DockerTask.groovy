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
                    docker(repository: 'registry.address:port/namespace/repository:tag') {
                        description 'build docker image'
                        command DockerCommand.BUILD
                        dockerfileContents "FROM debian:jessie-slim"
                        workingSubDirectory "."
                        environmentVariables "what=EVER"
                        saveTheImageAsFile "image.iso"
                        doNotUseCachingWhenBuildingImage true
                    }
                }
            }

        }
    }
}