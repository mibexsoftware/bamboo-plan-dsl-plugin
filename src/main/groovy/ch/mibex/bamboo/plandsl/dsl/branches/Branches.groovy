package ch.mibex.bamboo.plandsl.dsl.branches

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static ch.mibex.bamboo.plandsl.dsl.branches.Branch.NotifyOnNewBranchesType

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class Branches extends BambooObject {
    private List<Branch> branches = new ArrayList<>()
    private AutoBranchManagement autoBranchManagement
    private BranchMerging branchMerging
    private NotifyOnNewBranchesType notificationsType = NotifyOnNewBranchesType.NOTIFY_COMMITTERS_FOR_FAVOURITED_BRANCHES
    private NewPlanBranchesTriggerType newPlanBranchesTriggerType = NewPlanBranchesTriggerType.SAME_AS_IN_PARENT_PLAN

    // just for testing
    protected Branches() {}

    Branches(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Plan branches can be created and deleted automatically based on branch creation and deletion in the
     * primary source repository.
     */
    void autoBranchManagement(@DelegatesTo(AutoBranchManagement) Closure closure) {
        def autoBranchManagement = new AutoBranchManagement()
        DslScriptHelper.execute(closure, autoBranchManagement)
        this.autoBranchManagement = autoBranchManagement
    }

    /**
     * Automatic merging can test the merge between branches and push changes back to the repository on a successful
     * build. This setting will be applied to all new plan branches.
     */
    void merging(@DelegatesTo(BranchMerging) Closure closure) {
        def branchMerging = new BranchMerging()
        DslScriptHelper.execute(closure, branchMerging)
        this.branchMerging = branchMerging
    }

    /**
     * Notification preferences are applied to all new plan branches.
     */
    void notifications(NotifyOnNewBranchesType notificationsType) {
        this.notificationsType = notificationsType
    }

    /**
     * How new plan branches should be triggered.
     */
    void triggers(NewPlanBranchesTriggerType newPlanBranchesTriggerType) {
        this.newPlanBranchesTriggerType = newPlanBranchesTriggerType
    }

    /**
     * Configures a new plan branch.
     *
     * @param name The name of the new plan branch.
     */
    Branch branch(String name, @DelegatesTo(Branch) Closure closure) {
        def branch = new Branch(name)
        DslScriptHelper.execute(closure, branch)
        branches << branch
        branch
    }

    /**
     * Configures a new plan branch.
     *
     * @param params The mandatory parameters for the new plan branch. Only "name" is currently supported.
     */
    Branch branch(Map<String, String> params, @DelegatesTo(Branch) Closure closure) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        branch(params['name'], closure)
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
