package dsls.scms

project("BITBUCKETGIT") {
    name "Simple project"

    plan("BITBUCKETGIT") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        scm {
            bitbucketCloud(name: "myBitbucketGitRepo") {
                repoSlug "project_1/java-maven-simple"
                branch "develop"
                passwordAuth {
                    userName "admin"
                    password "pw"
                }
            }
        }
    }
}