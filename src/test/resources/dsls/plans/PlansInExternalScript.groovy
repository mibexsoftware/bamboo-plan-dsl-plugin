package dsls.plans

import dsls.plans.commons.MyUtilities

def globalPlan = plan('PLAN0', 'my new plan')

project('PROJECT') {
    name 'my project'

    def plan1 = plan('PLAN1') {
        name 'plan 1'
    }
    def plan2 = plan('PLAN2') {
        name 'plan 2'
    }
    MyUtilities.addCommonBranchSettings(plan1)
    MyUtilities.addCommonBranchSettings(plan2)
    MyUtilities.addCommonBranchSettings(globalPlan)
    plan(globalPlan)
}