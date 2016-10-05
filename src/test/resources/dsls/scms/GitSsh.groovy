package dsls.scms

project("GITSCM") {
    name "Simple project"

    plan("GITSCM") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        scm {
            git("myGitRepo") {
                url "http://localhost:7990/bitbucket/scm/project_1/java-maven-simple.git"
                branch "master"
                sshPrivateKey {
                    privateKey  """-----BEGIN RSA PRIVATE KEY-----
Proc-Type: 4,ENCRYPTED
-----END RSA PRIVATE KEY-----"""
                    passPhrase "test"
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