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
                sslClientCertificate {
                    privateKey "/a/b/c.key"
                    passPhrase "pw"
                }
            }
        }
    }
}