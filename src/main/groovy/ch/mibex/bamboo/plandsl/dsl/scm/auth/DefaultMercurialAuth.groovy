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

    // just for testing
    protected DefaultMercurialAuth() {}
}
