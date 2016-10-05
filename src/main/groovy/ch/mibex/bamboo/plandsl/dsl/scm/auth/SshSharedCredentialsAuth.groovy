package ch.mibex.bamboo.plandsl.dsl.scm.auth

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TypeChecked

@EqualsAndHashCode
@ToString
@TypeChecked
class SshSharedCredentialsAuth extends AuthType {
    String name

    SshSharedCredentialsAuth(String name) {
        this.name = name
    }

}
