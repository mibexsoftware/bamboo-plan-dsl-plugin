package ch.mibex.bamboo.plandsl.dsl.branches

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class BranchMerging extends BambooObject {
    private MergeStrategy mergeStrategy

    // just for testing
    protected BranchMerging() {}

    BranchMerging(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * The Branch Updater should be used when you want to:
     *
     * <ul>
     * <li> Automatically merge changes from the team's master branch into your feature branch, after a successful build
     * of the master branch.</li>
     * <li>Get notified when the changes on your feature branch are no longer compatible with the team's master
     * branch.</li>
     * </ul>
     */
    void gateKeeper(@DelegatesTo(GateKeeper) Closure closure) {
        def gateKeeper = new GateKeeper()
        DslScriptHelper.execute(closure, gateKeeper)
        this.mergeStrategy = gateKeeper
    }

    /**
     * The Gatekeeper should be used when you want to:
     * <ul>
     * <li>Automatically merge your feature branch back into the team's master branch, after a successful build
     * of the merged changes from both branches.</li>
     * <li>Get notified when a build of combined changes from both branches fails, preventing the feature branch
     * from being merged back into the team's master branch.</li>
     */
    void branchUpdater(@DelegatesTo(BranchUpdater) Closure closure) {
        def branchUpdater = new BranchUpdater()
        DslScriptHelper.execute(closure, branchUpdater)
        this.mergeStrategy = branchUpdater
    }

}
