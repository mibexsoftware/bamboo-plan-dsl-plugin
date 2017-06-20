---
title: Stage
position: 1.0
right_code: |
  ~~~ groovy
  plan(key: 'PLAN1KEY', name: 'my plan 1') {
      stage(name: 'package') {
          description 'packages the software'
          manual false

          job(key: 'TEST', name: 'tests the software') {
          }
          // more jobs...
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  plans:
    - key: PLAN1KEY
      name: my plan 1
      stages:
        - name: mystage
          description: packages the software
          manual: false
          jobs:
            - key: TEST
              name: tests the software
  ~~~
  {: title="YAML" }
---
A stage consists of a name, a description, a manual field (should the stage run automatically or not) and multiple jobs.
