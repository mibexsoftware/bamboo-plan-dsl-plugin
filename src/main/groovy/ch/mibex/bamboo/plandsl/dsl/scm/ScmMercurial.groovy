package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.RequiresBambooVersion
import ch.mibex.bamboo.plandsl.dsl.scm.auth.AuthType
import ch.mibex.bamboo.plandsl.dsl.scm.auth.DefaultMercurialAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SharedCredentialsAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SshAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SshWithoutPassphraseAuth
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedHgMercurialOptions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class ScmMercurial extends ScmType {
    String repositoryUrl
    String branch
    AuthType authType
    AdvancedHgMercurialOptions advancedOptions

    // for tests:
    protected ScmMercurial() {}

    ScmMercurial(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void repositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl
    }

    void branch(String branch) {
        this.branch = branch
    }

    @RequiresBambooVersion(minimumVersion = "5.13")
    void sharedCredentialsPasswordAuth(String name) {
        bambooFacade.requireSharedCredentials(name)
        authType = new SharedCredentialsAuth(SharedCredentialsAuth.SharedCredentialsType.USERNAMEPW, name)
    }

    void passwordAuth(@DelegatesTo(PasswordAuth) Closure closure) {
        authType = new PasswordAuth()
        DslScriptHelper.execute(closure, authType)
    }

    void keyfileWithoutPassphrase(@DelegatesTo(SshWithoutPassphraseAuth) Closure closure) {
        authType = new SshWithoutPassphraseAuth()
        DslScriptHelper.execute(closure, authType)
    }

    void keyfileWithPassphrase(@DelegatesTo(SshAuth) Closure closure) {
        authType = new SshWithoutPassphraseAuth()
        DslScriptHelper.execute(closure, authType)
    }

    void defaultMercurialCredentials(@DelegatesTo(DefaultMercurialAuth) Closure closure) {
        authType = new DefaultMercurialAuth()
        DslScriptHelper.execute(closure, authType)
    }

    void advancedOptions(@DelegatesTo(AdvancedHgMercurialOptions) Closure closure) {
        advancedOptions = new AdvancedHgMercurialOptions()
        DslScriptHelper.execute(closure, advancedOptions)
    }

}
