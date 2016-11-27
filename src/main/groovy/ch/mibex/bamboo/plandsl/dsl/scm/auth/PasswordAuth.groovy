package ch.mibex.bamboo.plandsl.dsl.scm.auth

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class PasswordAuth extends AuthType {
    private String userName
    private String password

    PasswordAuth(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected PasswordAuth() {}

    /**
     * Username you want to use to authenticate with.
     */
    void userName(String userName) {
        this.userName = userName
    }

    /**
     * The password required to access the repository.
     */
    void password(String password) {
        this.password = password
    }
}
