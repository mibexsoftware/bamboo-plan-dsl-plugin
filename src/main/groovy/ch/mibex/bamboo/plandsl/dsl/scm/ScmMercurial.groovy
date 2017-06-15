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

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class ScmMercurial extends ScmType {
    private String repositoryUrl
    private String branch
    private AuthType authType
    private AdvancedHgMercurialOptions advancedOptions

    // for tests:
    protected ScmMercurial() {}

    ScmMercurial(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * The URL of Mercurial repository.
     */
    void repositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl
    }

    /**
     * The name of the branch containing source code.
     */
    void branch(String branch) {
        this.branch = branch
    }

    @RequiresBambooVersion(minimumVersion = '5.13')
    void sharedCredentialsPasswordAuth(String name) {
        bambooFacade.requireSharedCredentials(name)
        authType = new SharedCredentialsAuth(SharedCredentialsAuth.SharedCredentialsType.USERNAMEPW, name, bambooFacade)
    }

    void passwordAuth(@DelegatesTo(value = PasswordAuth, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        authType = new PasswordAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    void keyfileWithoutPassphrase(
            @DelegatesTo(value = SshWithoutPassphraseAuth, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        authType = new SshWithoutPassphraseAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    void keyfileWithPassphrase(@DelegatesTo(value = SshAuth, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        authType = new SshWithoutPassphraseAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    void defaultMercurialCredentials(
            @DelegatesTo(value = DefaultMercurialAuth, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        authType = new DefaultMercurialAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    void advancedOptions(
            @DelegatesTo(value = AdvancedHgMercurialOptions, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        advancedOptions = new AdvancedHgMercurialOptions(bambooFacade)
        DslScriptHelper.execute(closure, advancedOptions)
    }

}
