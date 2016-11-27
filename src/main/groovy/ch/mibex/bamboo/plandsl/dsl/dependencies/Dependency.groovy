package ch.mibex.bamboo.plandsl.dsl.dependencies

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Dependency extends BambooObject {
    private String planKey

    // just for testing
    protected Dependency() {}

    Dependency(String planKey, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.planKey = planKey
    }
}
