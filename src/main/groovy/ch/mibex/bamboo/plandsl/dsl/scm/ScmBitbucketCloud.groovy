package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.RequiresBambooVersion
import ch.mibex.bamboo.plandsl.dsl.scm.auth.AuthType
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SharedCredentialsAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SshAuth
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class ScmBitbucketCloud extends ScmType {
    String repoSlug
    String branch
    AuthType authType
    ScmType scmType

    // for tests:
    protected ScmBitbucketCloud() {}

    ScmBitbucketCloud(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void repoSlug(String repoSlug) {
        this.repoSlug = repoSlug
    }

    void branch(String branch) {
        this.branch = branch
    }

    void git(@DelegatesTo(ScmBitbucketGit) Closure closure) {
        def git = new ScmBitbucketGit(bambooFacade)
        DslScriptHelper.execute(closure, git)
        scmType = git
    }

    void mercurial(@DelegatesTo(ScmBitbucketHg) Closure closure) {
        def hg = new ScmBitbucketHg(bambooFacade)
        DslScriptHelper.execute(closure, hg)
        scmType = hg
    }

    @RequiresBambooVersion(minimumVersion = '5.13')
    void sharedCredentialsPasswordAuth(String name) {
        bambooFacade.requireSharedCredentials(name)
        authType = new SharedCredentialsAuth(SharedCredentialsAuth.SharedCredentialsType.USERNAMEPW, name)
    }

    @RequiresBambooVersion(minimumVersion = '5.13')
    void sshPrivateKey(@DelegatesTo(SshAuth) Closure closure) {
        authType = new SshAuth()
        DslScriptHelper.execute(closure, authType)
    }

    @RequiresBambooVersion(minimumVersion = '5.13')
    void sshSharedCredentials(String name) {
        bambooFacade.requireSharedCredentials(name)
        authType = new SharedCredentialsAuth(SharedCredentialsAuth.SharedCredentialsType.SSH, name)
    }

    void passwordAuth(@DelegatesTo(PasswordAuth) Closure closure) {
        def authByPassword = new PasswordAuth()
        DslScriptHelper.execute(closure, authByPassword)
        authType = authByPassword
    }

}
