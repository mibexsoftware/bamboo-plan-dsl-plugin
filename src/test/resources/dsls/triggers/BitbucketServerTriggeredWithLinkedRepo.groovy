package dsls.triggers

project("BITBUCKETSERVER") {
    name "Simple project"

    plan("BITBUCKETSERVER") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        triggers {
            bitbucketServerRepositoryTriggered("run when new code") {
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

                        repository("myBitbucketServerRepo") {
                            checkoutDirectory "a/b"
                        }

                    }
                }
            }
        }

        scm {
            linkedRepository("myBitbucketServerRepo")
        }
    }
}