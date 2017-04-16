package ch.mibex.bamboo.plandsl.dsl.scm.auth

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class SshWithoutPassphraseAuth extends AuthType {
    private String privateKey
    private boolean enableSshCompression

    // just for testing:
    protected SshWithoutPassphraseAuth() {}

    SshWithoutPassphraseAuth(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * SSH private key you want to use to access the repository.
     */
    void privateKey(String privateKey) {
        this.privateKey = privateKey
    }

    void enableSshCompression(boolean enableSshCompression = true) {
        this.enableSshCompression = enableSshCompression
    }

}
