package ch.mibex.bamboo.plandsl.dsl.scm.web

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject

abstract class WebRepositoryType extends BambooObject {

    WebRepositoryType(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected WebRepositoryType() {}

}
