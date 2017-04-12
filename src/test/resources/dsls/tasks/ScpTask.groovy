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
                    scp(host: 'localhost', userName: 'bob') {
                        description 'Ship it to remote server'
                        passwordAuth {
                            password env('MY_PASSWORD_VARIABLE')
                        }
                        artifactByLocalPath(localPath: '*.zip,*.jar') {
                            useAntPatternsToSelectFiles()
                        }
                        remotePath 'a/b'
                        advancedOptions {
                            hostFingerprint 'test'
                            port 22
                        }
                    }
                }
            }
        }
    }
}