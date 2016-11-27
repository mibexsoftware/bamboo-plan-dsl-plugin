package dsls.branches.commons

import ch.mibex.bamboo.plandsl.dsl.branches.Branches

static def autoBranchingWithDevBranch(Branches branches, String branchPrefix, String devPlanBranchName, String devBranchName) {
    branches.with {
        autoBranchManagement {
            newPlanBranchesForMatchingBranchNames "$branchPrefix.*"
            deleteInactivePlanBranchesAfterDays 30
            doNotDeleteInactivePlanBranches()
        }

        branch(devPlanBranchName) {
            overridePlansDefaultRepository {
                branchName devBranchName
            }
            description 'my developer branch'
            enabled true
        }
    }
}