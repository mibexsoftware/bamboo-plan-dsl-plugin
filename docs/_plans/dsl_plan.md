---
title: Plan Definitions
position: 1.0
right_code: |
  ~~~ groovy
  project(key: 'PROJECTKEY', name: 'project name') {
      plan(key: 'PLANKEY1', name: 'plan name') {
          description 'Description for plan'
          enabled true

          stage(name: 'package') {
              // jobs
          }
          stage(name: 'test') {
              // jobs
          }

          deploymentProject(name: 'staging') {
              // deployment project details
          }
          deploymentProject(name: 'production') {
              // deployment project details
          }

          scm {
              // repository definitions
          }

          triggers {
              // plan triggers
          }

          branches {
              // branch configuration
          }

          notifications {
              // notification settings
          }
      }
  }
  ~~~
  {: title="DSL" }

---
As you can see, the plan definition contains all the elements you can also find in the Bamboo plan configuration GUI. Note that you only need to specify the elements you really need.