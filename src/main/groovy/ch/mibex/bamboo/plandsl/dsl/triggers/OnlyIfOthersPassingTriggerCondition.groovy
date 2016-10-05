package ch.mibex.bamboo.plandsl.dsl.triggers

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class OnlyIfOthersPassingTriggerCondition {
    List<String> planKeys

    void planKeys(String... planKeys) {
        this.planKeys = planKeys
    }

}
