---
title: Plan Branches
position: 2.0
redirect_to: "/#branches/"
right_code: |
  ~~~ groovy
  plan(key: 'PLAN1KEY', name: 'my plan 1') {
      branches {
          autoBranchManagement {
          }
          merging {
          }
          notifications {
          }
          triggers(NewPlanBranchesTriggerType.RUN_NEW_PLAN_BRANCHES_MANUALLY)
          branch(name: 'develop') {
          }
          branch(name: 'feature_ui_improvements') {
              vcsBranchName 'feature/ui_improvements'
          }
      }
  }    
  ~~~
  {: title="DSL" }
  ~~~ yml
  ~~~
  {: title="YAML" }

---
The `branches` block both configures how you would like to work with plan branches as well as contains 
the plan branch configurations.
