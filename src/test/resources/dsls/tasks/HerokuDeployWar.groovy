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
                    herokuDeployWar("Deploy WAR to Heroku") {
                        enabled true
                        apiKey 'key'
                        appName 'myapp'
                        warFile 'my.war'
                    }
                }
            }
        }
    }
}