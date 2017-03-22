package ch.mibex.bamboo.plandsl.dsl.commons

import ch.mibex.bamboo.plandsl.dsl.Plan

class MyUtilities {
    static void addCommonBranchSettings(Plan plan) {
        plan.with {
            branches {
                autoBranchManagement {
                    newPlanBranchesForMatchingBranchNames("feature/*")
                    deletePlanBranchesAfterDays(7)
                    deleteInactivePlanBranchesAfterDays(14)
                }
            }
        }
    }
}