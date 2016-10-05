package ch.mibex.bamboo.plandsl.dsl.branches

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class BranchMerging {
    MergeStrategy mergeStrategy

    void gateKeeper(@DelegatesTo(GateKeeper) Closure closure) {
        def gateKeeper = new GateKeeper()
        DslScriptHelper.execute(closure, gateKeeper)
        this.mergeStrategy = gateKeeper
    }

    void branchUpdater(@DelegatesTo(BranchUpdater) Closure closure) {
        def branchUpdater = new BranchUpdater()
        DslScriptHelper.execute(closure, branchUpdater)
        this.mergeStrategy = branchUpdater
    }

}
