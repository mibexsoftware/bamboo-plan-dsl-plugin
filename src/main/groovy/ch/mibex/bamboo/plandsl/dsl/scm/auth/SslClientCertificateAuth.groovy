package ch.mibex.bamboo.plandsl.dsl.scm.auth

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class SslClientCertificateAuth extends AuthType {
    private String privateKey
    private String passPhrase

    SslClientCertificateAuth(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected SslClientCertificateAuth() {}

    /**
     * Enter the absolute path of the private key.
     */
    void privateKey(String privateKey) {
        this.privateKey = privateKey
    }

    /**
     * Enter the passphrase for your private key.
     */
    void passPhrase(String passPhrase) {
        this.passPhrase = passPhrase
    }

}
