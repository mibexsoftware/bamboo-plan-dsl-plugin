package ch.mibex.bamboo.plandsl.dsl.scm.auth

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TypeChecked

@EqualsAndHashCode
@ToString
@TypeChecked
class SslClientCertificateAuth extends AuthType {
    String privateKey
    String passPhrase

    void privateKey(String privateKey) {
        this.privateKey = privateKey
    }

    void passPhrase(String passPhrase) {
        this.passPhrase = passPhrase
    }

}
