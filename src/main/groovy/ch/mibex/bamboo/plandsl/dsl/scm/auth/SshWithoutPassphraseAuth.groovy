package ch.mibex.bamboo.plandsl.dsl.scm.auth

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TypeChecked

@EqualsAndHashCode
@ToString
@TypeChecked
class SshWithoutPassphraseAuth extends AuthType {
    String privateKey
    boolean enableSshCompression

    void privateKey(String privateKey) {
        this.privateKey = privateKey
    }

    void enableSshCompression(boolean enableSshCompression = true) {
        this.enableSshCompression = enableSshCompression
    }

}
