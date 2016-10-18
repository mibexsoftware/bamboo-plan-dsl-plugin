package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.RequiresBambooVersion
import ch.mibex.bamboo.plandsl.dsl.scm.auth.AuthType
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SharedCredentialsAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SshAuth
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedGitOptions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class ScmGit extends ScmType {
    String url
    String branch
    AuthType authType
    AdvancedGitOptions advancedOptions

    protected ScmGit(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // for tests:
    protected ScmGit() {}

    void url(String url) {
        this.url = url
    }

    void branch(String branch) {
        this.branch = branch
    }

    @RequiresBambooVersion(minimumVersion = '5.13')
    void sharedCredentialsPasswordAuth(String name) {
        bambooFacade.requireSharedCredentials(name)
        authType = new SharedCredentialsAuth(SharedCredentialsAuth.SharedCredentialsType.USERNAMEPW, name)
    }

    void passwordAuth(@DelegatesTo(PasswordAuth) Closure closure) {
        authType = new PasswordAuth()
        DslScriptHelper.execute(closure, authType)
    }

    void sshPrivateKey(@DelegatesTo(SshAuth) Closure closure) {
        authType = new SshAuth()
        DslScriptHelper.execute(closure, authType)
    }

    void sshSharedCredentials(String name) {
        bambooFacade.requireSharedCredentials(name)
        authType = new SharedCredentialsAuth(SharedCredentialsAuth.SharedCredentialsType.SSH, name)
    }

    void advancedOptions(@DelegatesTo(AdvancedGitOptions) Closure closure) {
        advancedOptions = new AdvancedGitOptions()
        DslScriptHelper.execute(closure, advancedOptions)
    }

}
