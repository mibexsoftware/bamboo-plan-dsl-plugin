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
                sshAuth {
                    privateKey """-----BEGIN RSA PRIVATE KEY-----
Proc-Type: 4,ENCRYPTED
-----END RSA PRIVATE KEY-----"""
                    passPhrase "pw"
                }
                advancedOptions {
                    detectChangesInExternals true
                    enableCommitIsolation false
                    autoDetectRootUrlForBranches true
                    autoDetectRootUrlForTags true
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