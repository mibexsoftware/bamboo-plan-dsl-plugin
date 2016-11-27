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

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class ScmGit extends ScmType {
    private String url
    private String branch
    private AuthType authType
    private AdvancedGitOptions advancedOptions

    protected ScmGit(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // for tests:
    protected ScmGit() {}

    /**
     * The URL of your Git repository.
     */
    void url(String url) {
        this.url = url
    }

    /**
     * The name of a branch or a tag that contains the source code.
     */
    void branch(String branch) {
        this.branch = branch
    }

    /**
     * @since 1.2.0
     */
    @RequiresBambooVersion(minimumVersion = '5.13')
    void sharedCredentialsPasswordAuth(String name) {
        bambooFacade.requireSharedCredentials(name)
        authType = new SharedCredentialsAuth(SharedCredentialsAuth.SharedCredentialsType.USERNAMEPW, name, bambooFacade)
    }

    void passwordAuth(@DelegatesTo(PasswordAuth) Closure closure) {
        authType = new PasswordAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    void sshPrivateKey(@DelegatesTo(SshAuth) Closure closure) {
        authType = new SshAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    void sshSharedCredentials(String name) {
        bambooFacade.requireSharedCredentials(name)
        authType = new SharedCredentialsAuth(SharedCredentialsAuth.SharedCredentialsType.SSH, name, bambooFacade)
    }

    void advancedOptions(@DelegatesTo(AdvancedGitOptions) Closure closure) {
        advancedOptions = new AdvancedGitOptions(bambooFacade)
        DslScriptHelper.execute(closure, advancedOptions)
    }

}
