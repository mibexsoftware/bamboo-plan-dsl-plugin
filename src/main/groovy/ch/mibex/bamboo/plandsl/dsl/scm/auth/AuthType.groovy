package ch.mibex.bamboo.plandsl.dsl.scm.auth

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject

abstract class AuthType extends BambooObject {

    AuthType(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected AuthType() {}

}
