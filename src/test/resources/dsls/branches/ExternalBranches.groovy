package dsls.branches

import dsls.branches.commons.MyBranches

project('ATLAS', 'Atlassian plug-ins') {

    plan('PLANDSL', 'Plan DSL for Bamboo Plugin') {
        description 'A Bamboo plug-in to create and maintain your build plans with a Groovy-based DSL'
        enabled true

        def branchSettings = branches()
        MyBranches.autoBranchingWithDevBranch(branchSettings, '', 'develop', 'develop')
    }
}
