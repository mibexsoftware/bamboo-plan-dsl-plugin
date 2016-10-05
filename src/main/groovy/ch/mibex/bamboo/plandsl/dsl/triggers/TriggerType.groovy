package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper

class TriggerType {
    boolean enabled = true // we want triggers to be enabled by default
    String displayName
    OnlyIfOthersPassingTriggerCondition onlyRunIfOtherPlansArePassing

    void onlyRunIfOtherPlansArePassing(@DelegatesTo(OnlyIfOthersPassingTriggerCondition) Closure closure) {
        onlyRunIfOtherPlansArePassing = new OnlyIfOthersPassingTriggerCondition()
        DslScriptHelper.execute(closure, onlyRunIfOtherPlansArePassing)
    }

    void enabled(boolean enabled) {
        this.enabled = enabled
    }

}
