package dsls.scms

project("GITSCM") {
    name "Simple project"

    plan("GITSCM") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        scm {
            git("test") {
                url "http://localhost:7990/bitbucket/scm/project_1/java.git"
                branch "master"
                passwordAuth {
                    userName "myuser"
                    password "mypw"
                }
            }
        }
    }
}