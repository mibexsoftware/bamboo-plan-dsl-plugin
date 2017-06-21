---
title: Environment triggers
position: 1.0
right_code: |
  ~~~groovy
  environment(name: 'staging', id: 1) {
      description 'Staging env'
      triggers {
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  environments:
    - name: staging
      id: 1
      description: Staging env
      triggers:
  ~~~
  {: title="YAML" }
---
The following trigger types are supported for [deployment project environments](#environments):

  - [Scheduled trigger](#env_scheduled_trigger)
  - [After successful stage trigger](#after_successful_stage)
  - [After successful build plan trigger](#after_successful_build_plan)
  - [After successful deployment trigger](#after_successful_deployment)
