package ch.mibex.bamboo.plandsl.dsl.branches

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.RequiresBambooVersion
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class AutoBranchManagement extends BambooObject {
    private DeletedBranchesStrategy deletedBranchesStrategy = DeletedBranchesStrategy.DO_NOT_DELETE_PLAN_BRANCHES
    private int deletePlanBranchesAfterDays
    private InactiveBranchesStrategy inactiveBranchesStrategy =
            InactiveBranchesStrategy.DO_NOT_DELETE_INACTIVE_PLAN_BRANCHES
    private int deleteInactivePlanBranchesAfterDays
    private NewBranchesStrategy newBranchesStrategy = NewBranchesStrategy.DO_NOT_CREATE_PLAN_BRANCHES
    private String matchingBranchesRegex

    AutoBranchManagement(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing
    protected AutoBranchManagement() {}

    /**
     * Automatically creates a plan branch for matching new branches.
     *
     * @param regex Use a regular expression to only create plan branches that match specific branch names
     */
    void newPlanBranchesForMatchingBranchNames(String regex) {
        newBranchesStrategy = NewBranchesStrategy.NEW_PLAN_BRANCHES_FOR_MATCHING_BRANCH_NAMES
        matchingBranchesRegex = regex
    }

    /**
     * Do not create plan branches for new branches.
     */
    void doNotCreatePlanBranches() {
        newBranchesStrategy = NewBranchesStrategy.DO_NOT_CREATE_PLAN_BRANCHES
    }

    /**
     * Create plan branches for all new branches.
     */
    void createPlanBranchesForAllNewBranches() {
        newBranchesStrategy = NewBranchesStrategy.NEW_PLAN_BRANCHES_FOR_ALL_NEW_BRANCHES
    }

    /**
     * Create plan branches when pull request is created.
     *
     * @since 1.7.1
     */
    @RequiresBambooVersion(minimumVersion = '6.0')
    void createPlanBranchesForNewPullRequests() {
        newBranchesStrategy = NewBranchesStrategy.NEW_PLAN_BRANCHES_FOR_PULL_REQUESTS
    }

    /**
     * Delete inactive plan branches after X days.
     *
     * @param days number of days after inactive plan branches get deleted.
     */
    void deleteInactivePlanBranchesAfterDays(int days) {
        inactiveBranchesStrategy = InactiveBranchesStrategy.DELETE_INACTIVE_PLAN_BRANCHES_AFTER_DAYS
        deleteInactivePlanBranchesAfterDays = days
    }

    /**
     * Do not delete inactive plan branches at all.
     */
    void doNotDeleteInactivePlanBranches() {
        inactiveBranchesStrategy = InactiveBranchesStrategy.DO_NOT_DELETE_INACTIVE_PLAN_BRANCHES
    }

    /**
     * Do not delete plan branches when their corresponding branch gets deleted.
     */
    void doNotDeletePlanBranches() {
        deletedBranchesStrategy = DeletedBranchesStrategy.DO_NOT_DELETE_PLAN_BRANCHES
    }

    /**
     * Delete plan branches after X days after their corresponding branches got deleted.
     *
     * @param days number of days after plan branches get deleted.
     */
    void deletePlanBranchesAfterDays(int days) {
        Validations.requireTrue(days >= 0, 'deletePlanBranchesAfterDays must be greater than zero')
        if (days == 0) {
            deletedBranchesStrategy = DeletedBranchesStrategy.DELETE_PLAN_BRANCHES_WITH_DAILY_CLEANUP
        } else {
            deletedBranchesStrategy = DeletedBranchesStrategy.DELETE_PLAN_BRANCHES_AFTER_DAYS
            deletePlanBranchesAfterDays = days
        }
    }

    /**
     * Delete plan branches with daily cleanup
     */
    void deletePlanBranchesWithDailyCleanup() {
        deletedBranchesStrategy = DeletedBranchesStrategy.DELETE_PLAN_BRANCHES_WITH_DAILY_CLEANUP
    }

    static enum NewBranchesStrategy {
        NEW_PLAN_BRANCHES_FOR_MATCHING_BRANCH_NAMES,
        DO_NOT_CREATE_PLAN_BRANCHES,
        NEW_PLAN_BRANCHES_FOR_ALL_NEW_BRANCHES,
        NEW_PLAN_BRANCHES_FOR_PULL_REQUESTS
    }

    static enum InactiveBranchesStrategy {
        DELETE_INACTIVE_PLAN_BRANCHES_AFTER_DAYS,
        DO_NOT_DELETE_INACTIVE_PLAN_BRANCHES
    }

    static enum DeletedBranchesStrategy {
        DELETE_PLAN_BRANCHES_WITH_DAILY_CLEANUP,
        DO_NOT_DELETE_PLAN_BRANCHES,
        DELETE_PLAN_BRANCHES_AFTER_DAYS
    }

}
