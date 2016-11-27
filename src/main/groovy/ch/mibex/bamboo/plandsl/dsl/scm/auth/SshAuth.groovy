package ch.mibex.bamboo.plandsl.dsl.scm.auth

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class SshAuth extends AuthType {
    private String privateKey
    private String passPhrase

    SshAuth(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected SshAuth() {}

    /**
     * SSH private key you want to use to access the repository.
     */
    void privateKey(String privateKey) {
        this.privateKey = privateKey
    }

    /**
     * Passphrase you want to use to access SSH private key.
     */
    void passPhrase(String passPhrase) {
        this.passPhrase = passPhrase
    }
}
