package dsls.scms

project("PERFORCESCM") {
    name "Simple project"

    plan("PERFORCESCM") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        scm {
            perforce(name: "myPerforceRepo") {
                port "9091"
                client "perforce"
                depotView "//perforce/workspace"
                environmentVariables "P4CHARSET=\"utf8\""
                letBambooManageWorkspace true
                useClientMapping true
                passwordAuth {
                    userName "admin"
                    password "pw"
                }
                advancedOptions {
                    quietPeriod {
                        waitTimeInSeconds 120
                        maximumRetries 3
                    }
                    includeExcludeFiles(MatchType.EXCLUDE_ALL_MATCHING_CHANGES) {
                        filePattern "exe"
                    }
                    excludeChangesetsRegex "FIXES .*"
                    webRepository {
                        fisheye {
                            url "http://localhost:7990"
                            repositoryPath "a/b/c"
                            repositoryName "d"
                        }
                    }
                }
            }
        }
    }
}