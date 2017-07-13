package ch.mibex.bamboo.plandsl.dsl.scm.auth

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class DefaultMercurialAuth extends AuthType {

    DefaultMercurialAuth(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // single-arg ctor necessary for !defaultMercurialAuth
    protected DefaultMercurialAuth(String e) {
    }

    // just for testing
    protected DefaultMercurialAuth() {}
}
