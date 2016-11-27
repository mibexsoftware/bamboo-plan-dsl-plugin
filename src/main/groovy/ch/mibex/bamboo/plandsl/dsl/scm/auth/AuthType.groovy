package ch.mibex.bamboo.plandsl.dsl.scm.auth

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
abstract class AuthType extends BambooObject {

    AuthType(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected AuthType() {}

}
