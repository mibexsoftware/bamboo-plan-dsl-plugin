package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.auth.AuthType
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SshAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SslClientCertificateAuth
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedSvnOptions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class ScmSubversion extends ScmType {
    String repositoryUrl
    String userName
    AuthType authType
    AdvancedSvnOptions advancedOptions

    // for tests:
    protected ScmSubversion() {}

    ScmSubversion(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void repositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl
    }

    void userName(String userName) {
        this.userName = userName
    }

    void passwordAuth(@DelegatesTo(PasswordAuth) Closure closure) {
        authType = new PasswordAuth()
        DslScriptHelper.execute(closure, authType)
    }

    void sshAuth(@DelegatesTo(SshAuth) Closure closure) {
        authType = new SshAuth()
        DslScriptHelper.execute(closure, authType)
    }

    void sslClientCertificate(@DelegatesTo(SslClientCertificateAuth) Closure closure) {
        authType = new SslClientCertificateAuth()
        DslScriptHelper.execute(closure, authType)
    }

    void advancedOptions(@DelegatesTo(AdvancedSvnOptions) Closure closure) {
        advancedOptions = new AdvancedSvnOptions()
        DslScriptHelper.execute(closure, advancedOptions)
    }
}
