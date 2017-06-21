---
title: After successful stage trigger
position: 1.3
right_code: |
  ~~~groovy
  environment(name: 'staging', id: 1) {
      triggers {
          afterSuccessfulStage() {
              planStageToTriggerThisDeployment 'STAGE1'
              customPlanBranchName 'TEST'
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
        - !afterSuccessfulStageDeployment
          planStageToTriggerThisDeployment: STAGE1
          customPlanBranchName: TEST
  ~~~
  {: title="YAML" }
---
Deployment is started after a [stage](#stage) is successfully built.
