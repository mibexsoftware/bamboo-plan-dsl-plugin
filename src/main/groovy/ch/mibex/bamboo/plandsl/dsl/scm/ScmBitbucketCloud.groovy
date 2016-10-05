package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class ScmBitbucketCloud extends ScmType {
    String repoSlug
    String branch
    PasswordAuth authType
    ScmType scmType

    void repoSlug(String repoSlug) {
        this.repoSlug = repoSlug
    }

    void branch(String branch) {
        this.branch = branch
    }

    void git(@DelegatesTo(ScmBitbucketGit) Closure closure) {
        def git = new ScmBitbucketGit()
        DslScriptHelper.execute(closure, git)
        scmType = git
    }

    void mercurial(@DelegatesTo(ScmBitbucketHg) Closure closure) {
        def hg = new ScmBitbucketHg()
        DslScriptHelper.execute(closure, hg)
        scmType = hg
    }

    void passwordAuth(@DelegatesTo(PasswordAuth) Closure closure) {
        def authByPassword = new PasswordAuth()
        DslScriptHelper.execute(closure, authByPassword)
        authType = authByPassword
    }

}
