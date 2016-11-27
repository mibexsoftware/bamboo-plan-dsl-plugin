package ch.mibex.bamboo.plandsl.dsl.scm.auth

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class SharedCredentialsAuth extends AuthType {
    private SharedCredentialsType sharedCredentialsType
    private String name

    SharedCredentialsAuth(SharedCredentialsType sharedCredentialsType, String name, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.sharedCredentialsType = sharedCredentialsType
        this.name = name
    }

    // just for testing:
    protected SharedCredentialsAuth() {}

    static enum SharedCredentialsType {
        AWS, SSH, USERNAMEPW
    }
}
