package ch.mibex.bamboo.plandsl.dsl.branches

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class GateKeeper extends MergeStrategy {

    GateKeeper(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing
    protected GateKeeper() {}

    /**
     * Choose the branch with which to merge your changes (and to which changes should be pushed).
     */
    void checkoutBranch(String planBranchKey) {
        this.planBranchKey = planBranchKey
    }

}
