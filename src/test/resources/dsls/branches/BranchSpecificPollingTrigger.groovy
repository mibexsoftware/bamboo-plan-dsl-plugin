package dsls.branches

project("SIMPLEPROJECT2") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        branches {
            branch("develop") {
                triggers {
                    polling {
                        periodically {
                            pollingFrequencyInSecs 30
                        }
                        repositories("repo123", "repo456")
                    }
                }
            }
        }
    }
}