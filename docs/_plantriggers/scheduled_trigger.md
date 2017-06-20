---
title: Scheduled trigger
position: 1.2
right_code: |
  ~~~groovy
  triggers {
      scheduled() {
          description 'scheduled'
          cronExpression '0 0 0 ? * *'
          onlyRunIfOtherPlansArePassing {
              planKeys 'PROJKEY-PLANKEY'
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  triggers:
    - !scheduled
      description scheduled
      cronExpression: '0 0 0 ? * *'
      onlyRunIfOtherPlansArePassing:
        planKeys: [PROJKEY-PLANKEY]
  ~~~
  {: title="YAML" }
---
Run a plan according to a schedule.