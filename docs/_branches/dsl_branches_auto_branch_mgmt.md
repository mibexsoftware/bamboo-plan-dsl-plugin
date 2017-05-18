---
title: Auto Branch Management
position: 2.1
right_code: |
  ~~~groovy
  project("SIMPLEPROJECT2") {
      name "Simple project"

      plan("SIMPLEPLAN") {
          name "Simple plan"
          description "this is a simple plan"
          enabled true

          branches {
              autoBranchManagement {
                  createPlanBranchesForNewPullRequests()
                  deletePlanBranchesAfterDays(7)
                  deleteInactivePlanBranchesAfterDays(14)
              }
          }
      }
  }
  ~~~
  {: title="DSL" }

---
The `branches` block both configures how you would like to work with plan branches on a plan level as well as contains the plan branch definitions: