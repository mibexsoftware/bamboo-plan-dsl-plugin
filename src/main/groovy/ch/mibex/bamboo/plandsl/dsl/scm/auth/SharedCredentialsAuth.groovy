package ch.mibex.bamboo.plandsl.dsl.scm.auth

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TypeChecked

@EqualsAndHashCode
@ToString
@TypeChecked
class SharedCredentialsAuth extends AuthType {
    SharedCredentialsType sharedCredentialsType
    String name

    SharedCredentialsAuth(SharedCredentialsType sharedCredentialsType, String name) {
        this.sharedCredentialsType = sharedCredentialsType
        this.name = name
    }

    static enum SharedCredentialsType {
        AWS, SSH, USERNAMEPW
    }
}
