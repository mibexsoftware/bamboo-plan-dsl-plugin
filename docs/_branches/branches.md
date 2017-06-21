---
title: Plan branches
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
          notifications(Branch.NotifyOnNewBranchesType.USE_PLANS_NOTIFICATION_SETTINGS)
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
  plans:
    - key: PLAN1KEY
      name: my plan 1
      branches:
        autoBranchManagement:
        merging:
        notifications: !notifyOnNewBranchesType USE_PLANS_NOTIFICATION_SETTINGS
        triggers: !newPlanBranchesTriggerType RUN_NEW_PLAN_BRANCHES_MANUALLY
        branches:
          - name: develop
          - name: feature_ui_improvements
            vcsBranchName: feature/ui_improvements
  ~~~
  {: title="YAML" }

---
The plan branches section both configures how you would like to work with plan branches as well as contains
the individual plan branch configurations.
