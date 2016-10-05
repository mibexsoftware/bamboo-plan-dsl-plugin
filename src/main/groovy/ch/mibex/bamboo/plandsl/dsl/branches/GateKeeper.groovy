package ch.mibex.bamboo.plandsl.dsl.branches

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class GateKeeper extends MergeStrategy {

    void checkoutBranch(String planBranchKey) {
        this.planBranchKey = planBranchKey
    }

}
