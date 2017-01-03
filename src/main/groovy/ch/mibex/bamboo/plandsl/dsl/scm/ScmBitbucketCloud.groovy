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

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class ScmBitbucketCloud extends ScmType {
    private String repoSlug
    private String branch
    private AuthType authType
    private ScmType scmType = new ScmBitbucketGit(bambooFacade) // default is Git

    // for tests:
    protected ScmBitbucketCloud() {}

    ScmBitbucketCloud(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Choose the repository you want to use for your Plan.
     *
     * @param repoSlug The slug of the repository.
     */
    void repoSlug(String repoSlug) {
        this.repoSlug = repoSlug
    }

    /**
     * Choose a branch you want to check out your code from.
     */
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

    /**
     * @since 1.2.0
     */
    @RequiresBambooVersion(minimumVersion = '5.13')
    void sharedCredentialsPasswordAuth(String name) {
        bambooFacade.requireSharedCredentials(name)
        authType = new SharedCredentialsAuth(SharedCredentialsAuth.SharedCredentialsType.USERNAMEPW, name, bambooFacade)
    }

    @RequiresBambooVersion(minimumVersion = '5.13')
    void sshPrivateKey(@DelegatesTo(SshAuth) Closure closure) {
        authType = new SshAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    @RequiresBambooVersion(minimumVersion = '5.13')
    void sshSharedCredentials(String name) {
        bambooFacade.requireSharedCredentials(name)
        authType = new SharedCredentialsAuth(SharedCredentialsAuth.SharedCredentialsType.SSH, name, bambooFacade)
    }

    void passwordAuth(@DelegatesTo(PasswordAuth) Closure closure) {
        def authByPassword = new PasswordAuth(bambooFacade)
        DslScriptHelper.execute(closure, authByPassword)
        authType = authByPassword
    }

}
