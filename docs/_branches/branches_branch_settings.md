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
  branches:
    - name: feature_ui_improvements
      vcsBranchName: feature/ui_improvements
      description: my feature branch
      enabled: true
      cleanupAutomatically: true
      triggers:
        - !scheduled
          cronExpression: * * * * * * *
          onlyRunIfOtherPlansArePassing:
            planKeys: [SEED-SEED-JOB1]
      merging:
        mergeStrategy: !branchUpdater
          planBranchKey: 'SIMPLEPROJECT2-SIMPLEPLAN0'
          pushEnabled: false
      variables:
        - key: name
          value: value 
      notifications: !notifyOnNewBranchesType USE_PLANS_NOTIFICATION_SETTINGS
  ~~~
  {: title="YAML" }
---
This configures the setting for a specific plan branch. A plan branch can have triggers, merging settings, build
variables and notification settings.