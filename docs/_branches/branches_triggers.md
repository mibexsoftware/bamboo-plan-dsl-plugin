---
title: Triggers
description: Branch specific triggers
position: 2.4
right_code: |
  ~~~groovy
  branches {
      branch("develop") {
          triggers {
              polling {
                  periodically {
                      pollingFrequencyInSecs 30
                  }
                  repositories('repo123', 'repo456')
              }
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  ~~~
  {: title="YAML" }
---
This defines how new plan branches should be triggered.
