---
title: Auto Branch Management
position: 2.1
right_code: |
  ~~~groovy
  branches {
      autoBranchManagement {
          createPlanBranchesForNewPullRequests()
          deletePlanBranchesAfterDays 7
          deleteInactivePlanBranchesAfterDays 14
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  branches:
    autoBranchManagement:
      newBranchesStrategy: !newBranches NEW_PLAN_BRANCHES_FOR_PULL_REQUESTS
      deletedBranchesStrategy: !deletedBranches DELETE_PLAN_BRANCHES_AFTER_DAYS
      deletePlanBranchesAfterDays: 7
      inactiveBranchesStrategy: !inactiveBranches DELETE_INACTIVE_PLAN_BRANCHES_AFTER_DAYS
      deleteInactivePlanBranchesAfterDays: 14
  ~~~
  {: title="YAML" }

---
[Plan branches](#branches) can be created and deleted automatically based on branch creation and deletion settings for the
primary source repository. This can be configured inside the auth branch management section.