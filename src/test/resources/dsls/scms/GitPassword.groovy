package dsls.scms

project("GITSCM") {
    name "Simple project"

    plan("GITSCM") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        scm {
            git(name: "myGitRepo") {
                url "http://localhost:7990/bitbucket/scm/project_1/java-maven-simple.git"
                branch "master"
                passwordAuth {
                    userName "admin"
                    password "pw"
                }
                advancedOptions {
                    useShallowClones true
                    enableRepositoryCachingOnRemoteAgents true
                    useSubmodules true
                    commandTimeoutInMinutes 20
                    verboseLogs true
                    fetchWholeRepository true
                    enableLfsSupport true
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