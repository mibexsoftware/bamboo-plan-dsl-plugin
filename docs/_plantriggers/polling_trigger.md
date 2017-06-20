---
title: Polling trigger
position: 1.4
right_code: |
  ~~~groovy
  triggers {
      polling() {
          description 'mypollsched'
          repositories 'mygit', 'mybitbucket'
          scheduled {
              cronExpression '0 0 0 ? * *'
          }
          onlyRunIfOtherPlansArePassing {
              planKeys 'PROJKEY-PLANKEY'
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  triggers:
    - !polling
      description: mypollsched
      scheduledTrigger:
        cronExpression: '0 0 0 ? * *'
      onlyRunIfOtherPlansArePassing:
        planKeys: [PROJKEY-PLANKEY]
  ~~~
  {: title="YAML" }
---
Bamboo polls source repository and builds when new changes are found.