package dsls.scms

project("GITHUBSCM") {
    name "Github SCM Project"

    plan("GITHUBSCM") {
        name "Github SCM Plan"
        description "this is a simple plan"
        enabled true

        scm {
            github(name: "myGithubRepo") {
                repoSlug "test/HelloWorld"
                branch "master"
                passwordAuth {
                    userName "test"
                    password "pw"
                }
                advancedOptions {
                    useShallowClones true
                    enableRepositoryCachingOnRemoteAgents true
                    useSubmodules true
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
        }
    }
}