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

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class ScmSubversion extends ScmType {
    private String repositoryUrl
    private String userName
    private AuthType authType
    private AdvancedSvnOptions advancedOptions

    // for tests:
    protected ScmSubversion() {}

    ScmSubversion(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * The location of the Subversion repository root (e.g. http://svn.collab.net//repos/svn)
     */
    void repositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl
    }

    /**
     * The subversion username (if any) required to access the repository
     */
    void userName(String userName) {
        this.userName = userName
    }

    void passwordAuth(@DelegatesTo(PasswordAuth) Closure closure) {
        authType = new PasswordAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    void sshAuth(@DelegatesTo(SshAuth) Closure closure) {
        authType = new SshAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    void sslClientCertificate(@DelegatesTo(SslClientCertificateAuth) Closure closure) {
        authType = new SslClientCertificateAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    void advancedOptions(@DelegatesTo(AdvancedSvnOptions) Closure closure) {
        advancedOptions = new AdvancedSvnOptions(bambooFacade)
        DslScriptHelper.execute(closure, advancedOptions)
    }
}
