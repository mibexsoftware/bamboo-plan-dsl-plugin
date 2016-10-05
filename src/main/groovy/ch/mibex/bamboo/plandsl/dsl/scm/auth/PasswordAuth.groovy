package ch.mibex.bamboo.plandsl.dsl.scm.auth

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TypeChecked

@EqualsAndHashCode
@ToString
@TypeChecked
class PasswordAuth extends AuthType {
    String userName
    String password

    void userName(String userName) {
        this.userName = userName
    }

    void password(String password) {
        this.password = password
    }
}
