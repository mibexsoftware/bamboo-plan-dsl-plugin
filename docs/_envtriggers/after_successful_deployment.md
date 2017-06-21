---
title: After successful deployment trigger
position: 1.4
right_code: |
  ~~~groovy
  environment(name: 'staging', id: 1) {
      triggers {
          afterSuccessDeployment(triggeringEnvironment: 'PROD') {
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
          - !afterSuccessfulDeployment
            triggeringEnvironment: PROD
  ~~~
  {: title="YAML" }
---
Deployment is started after a deployment on another [environment](#environments) is completed successfully.