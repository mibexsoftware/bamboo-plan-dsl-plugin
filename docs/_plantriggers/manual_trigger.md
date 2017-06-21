---
title: Manual trigger
position: 1.3
right_code: |
  ~~~groovy
  triggers {
      manual() {
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  triggers:
    - !manual
      description: manually
  ~~~
  {: title="YAML" }
---
Trigger a build manually in the Bamboo UI.