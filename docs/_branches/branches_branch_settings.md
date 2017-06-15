---
title: Branch settings
description: Branch specific settings
position: 2.5
right_code: |
  ~~~groovy
  branches {
      branch(name: 'feature_ui_improvements') {
          vcsBranchName 'feature/ui_improvements'
          description 'my feature branch'
          enabled true
          cleanupAutomatically true

          triggers {
              scheduled {
                  cron('* * * * * * *')
                      onlyRunIfOtherPlansArePassing {
                      planKeys 'SEED-SEED-JOB1'
                  }
              }
          }
          merging {
              branchUpdater {
                  mergeFromBranch 'SIMPLEPROJECT2-SIMPLEPLAN0'
                  pushEnabled false
              }
          }
          variables {
              variable 'name', 'value'
          }
          notifications(NotifyOnNewBranchesType.USE_PLANS_NOTIFICATION_SETTINGS)
      }
  }  
  ~~~
  {: title="DSL" }
  ~~~ yml
  ~~~
  {: title="YAML" }
---
This configures the new plan branch(es).