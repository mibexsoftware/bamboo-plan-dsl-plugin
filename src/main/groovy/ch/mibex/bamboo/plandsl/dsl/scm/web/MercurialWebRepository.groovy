package ch.mibex.bamboo.plandsl.dsl.scm.web

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class MercurialWebRepository extends WebRepositoryType {

    MercurialWebRepository(BambooFacade bambooFacade) {
        super(bambooFacade)
    }
}
