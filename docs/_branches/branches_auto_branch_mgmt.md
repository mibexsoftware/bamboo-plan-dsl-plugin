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
  ~~~
  {: title="YAML" }

---
Plan branches can be created and deleted automatically based on branch creation and deletion in the
primary source repository. This can be configured inside the `autoBranchManagement` block.