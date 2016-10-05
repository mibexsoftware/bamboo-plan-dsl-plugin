package ch.mibex.bamboo.plandsl.dsl.branches

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class BranchUpdater extends MergeStrategy {

    void mergeFromBranch(String planBranchKey) {
        this.planBranchKey = planBranchKey
    }

}
