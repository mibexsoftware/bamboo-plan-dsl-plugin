package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class ScmLinkedRepository extends ScmType {
    // for tests:
    protected ScmLinkedRepository() {}

    ScmLinkedRepository(BambooFacade bambooFacade) {
        super(bambooFacade)
    }
}
