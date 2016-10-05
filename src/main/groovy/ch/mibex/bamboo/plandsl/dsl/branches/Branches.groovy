package ch.mibex.bamboo.plandsl.dsl.branches

import ch.mibex.bamboo.plandsl.dsl.DslParentElement
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static ch.mibex.bamboo.plandsl.dsl.branches.Branch.NotifyOnNewBranchesType

@ToString
@EqualsAndHashCode
class Branches implements DslParentElement<Branch> {
    Set<Branch> branches = new LinkedHashSet<>()
    AutoBranchManagement autoBranchManagement
    BranchMerging branchMerging
    NotifyOnNewBranchesType notificationsType = NotifyOnNewBranchesType.NOTIFY_COMMITTERS_FOR_FAVOURITED_BRANCHES
    NewPlanBranchesTriggerType newPlanBranchesTriggerType = NewPlanBranchesTriggerType.SAME_AS_IN_PARENT_PLAN

    void autoBranchManagement(@DelegatesTo(AutoBranchManagement) Closure closure) {
        def autoBranchManagement = new AutoBranchManagement()
        DslScriptHelper.execute(closure, autoBranchManagement)
        this.autoBranchManagement = autoBranchManagement
    }

    void merging(@DelegatesTo(BranchMerging) Closure closure) {
        def branchMerging = new BranchMerging()
        DslScriptHelper.execute(closure, branchMerging)
        this.branchMerging = branchMerging
    }

    void notifications(NotifyOnNewBranchesType notificationsType) {
        this.notificationsType = notificationsType
    }

    void triggers(NewPlanBranchesTriggerType newPlanBranchesTriggerType) {
        this.newPlanBranchesTriggerType = newPlanBranchesTriggerType
    }

    void branch(String name, @DelegatesTo(Branch) Closure closure) {
        def branch = new Branch(name)
        DslScriptHelper.execute(closure, branch)
        branches << branch
    }

    @Override
    Collection<Branch> children() {
        branches
    }

    static enum NewPlanBranchesTriggerType {
        SAME_AS_IN_PARENT_PLAN('inherited'),
        RUN_NEW_PLAN_BRANCHES_MANUALLY('manual')

        String bambooOption

        NewPlanBranchesTriggerType(String bambooOption) {
            this.bambooOption = bambooOption
        }
    }

}
