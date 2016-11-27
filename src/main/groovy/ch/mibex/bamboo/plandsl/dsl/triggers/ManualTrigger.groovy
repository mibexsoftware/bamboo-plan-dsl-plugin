package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class ManualTrigger extends TriggerType {

    // for tests
    protected ManualTrigger() {}

    protected ManualTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }
}
