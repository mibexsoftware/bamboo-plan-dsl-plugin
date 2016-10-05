package dsls.scms

project("GITSCM") {
    name "Simple project"

    plan("GITSCM") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        scm {
            git("myGitRepo") {
                url "http://localhost:7990/bitbucket/scm/project_1/java-simple.git"
                branch "develop"
                passwordAuth {
                    userName "user"
                    password "userpw"
                }
                advancedOptions {
                    useShallowClones false
                    enableRepositoryCachingOnRemoteAgents false
                    useSubmodules false
                    commandTimeoutInMinutes 25
                    verboseLogs false
                    fetchWholeRepository false
                    quietPeriod {
                        waitTimeInSeconds 110
                        maximumRetries 4
                    }
                    includeExcludeFiles(MatchType.EXCLUDE_ALL_MATCHING_CHANGES) {
                        filePattern "bat"
                    }
                    excludeChangesetsRegex "RESOLVES .*"
                    webRepository {
                        fisheye {
                            url "http://localhost:7991"
                            repositoryPath "a/b/c/d"
                            repositoryName "e"
                        }
                    }
                }
            }
        }
    }
}