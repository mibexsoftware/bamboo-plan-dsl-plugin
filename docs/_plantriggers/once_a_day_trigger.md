---
title: Once a day trigger
position: 1.6
right_code: |
  ~~~groovy
  triggers {
      onceAday {
          description 'run every day at noon'
          buildTime '12:00'
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  triggers:
    - !onceADay
      description run every day at noon
      buildTime: '12:00'
  ~~~
  {: title="YAML" }
---
Single daily build.