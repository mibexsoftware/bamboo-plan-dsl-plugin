package dsls.scms

project("SIMPLEPROJECT") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        scm {
            bitbucketCloud(name: "myBitbucketHgRepo") {
                mercurial {
                    advancedOptions {
                        commandTimeoutInMinutes 20
                        verboseLogs true
                        fetchWholeRepository true
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
                repoSlug "project_1/java-maven-simple"
                branch "master"
                passwordAuth {
                    userName "user"
                    password "pw"
                }
            }
        }
    }
}