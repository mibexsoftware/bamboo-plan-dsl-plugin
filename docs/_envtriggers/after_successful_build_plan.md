---
title: After successful build plan
position: 1.2
right_code: |
  ~~~groovy
  environment(name: 'staging', id: 1) {
      triggers {
          afterSuccessfulBuildPlan() {
              customPlanBranchName 'DEV'
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
        - !afterSuccessfulBuildDeployment
          customPlanBranchName: DEV
  ~~~
  {: title="YAML" }
---
Deployment is started after a [plan](#plan) is successfully built.