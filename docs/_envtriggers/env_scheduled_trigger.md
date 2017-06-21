---
title: Scheduled trigger
position: 1.1
right_code: |
  ~~~groovy
  environment(name: 'staging', id: 1) {
      triggers {
          scheduledCron(cronExpression: '0 0 0 6 * *') {
              customPlanBranchName: 'MYPLANBRANCH'
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  environments:
    - name: staging
      id: 1
      triggers:
        - !scheduledDeployment
          cronExpression: 0 0 0 6 * *
          customPlanBranchName: MYPLANBRANCH
  ~~~
  {: title="YAML" }
---
Run according to schedule.