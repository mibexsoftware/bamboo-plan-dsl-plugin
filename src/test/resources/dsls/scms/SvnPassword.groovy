package dsls.scms

project("SVNSCM") {
    name "Simple project"

    plan("SVNSCM") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        scm {
            subversion("mySvn") {
                repositoryUrl "http://svn.red-bean.com/repos/test"
                userName "admin"
                passwordAuth {
                    userName "admin"
                    password "pw"
                }
                advancedOptions {
                    detectChangesInExternals true
                    useSvnExport true
                    enableCommitIsolation true
                    autoDetectRootUrlForBranches false
                    branchesRootUrl "/branches"
                    autoDetectRootUrlForTags false
                    tagRootUrl "/tags"
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