package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.auth.AuthType
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SshAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SshSharedCredentialsAuth
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

    void url(String url) {
        this.url = url
    }

    void branch(String branch) {
        this.branch = branch
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
        authType = new SshSharedCredentialsAuth(name)
    }

    void advancedOptions(@DelegatesTo(AdvancedGitOptions) Closure closure) {
        advancedOptions = new AdvancedGitOptions()
        DslScriptHelper.execute(closure, advancedOptions)
    }

}
