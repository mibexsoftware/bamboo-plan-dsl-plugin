package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class OnlyIfOthersPassingTriggerCondition extends BambooObject {
    private List<String> planKeys

    // for tests
    protected OnlyIfOthersPassingTriggerCondition() {}

    protected OnlyIfOthersPassingTriggerCondition(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void planKeys(String... planKeys) {
        this.planKeys = planKeys
    }
}
