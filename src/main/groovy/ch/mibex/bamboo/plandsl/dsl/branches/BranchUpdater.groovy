package ch.mibex.bamboo.plandsl.dsl.branches

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class BranchUpdater extends MergeStrategy {

    BranchUpdater(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing
    protected BranchUpdater() {}

    /**
     * Choose the repo from which changes should be merged with your feature branch.
     */
    void mergeFromBranch(String planBranchKey) {
        this.planBranchKey = planBranchKey
    }

}
