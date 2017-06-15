package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class TriggerType extends BambooObject {
    protected boolean enabled = true // we want triggers to be enabled by default
    protected String description
    protected OnlyIfOthersPassingTriggerCondition onlyRunIfOtherPlansArePassing

    // for tests
    protected TriggerType() {}

    protected TriggerType(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void onlyRunIfOtherPlansArePassing(
            @DelegatesTo(value = OnlyIfOthersPassingTriggerCondition, strategy = Closure.DELEGATE_FIRST) Closure c) {
        onlyRunIfOtherPlansArePassing = new OnlyIfOthersPassingTriggerCondition()
        DslScriptHelper.execute(c, onlyRunIfOtherPlansArePassing)
    }

    void enabled(boolean enabled = true) {
        this.enabled = enabled
    }

    void description(String description) {
        this.description = description
    }

}
