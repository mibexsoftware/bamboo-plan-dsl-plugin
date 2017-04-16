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
                    ssh(host: 'localhost', userName: 'bob') {
                        description 'login to remote server'
                        keyWithoutPassphrase {
                            privateKey env('MY_PRIVATE_KEY')
                        }
                        command 'ls -l'
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