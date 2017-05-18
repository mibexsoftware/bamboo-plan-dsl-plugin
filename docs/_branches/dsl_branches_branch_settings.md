---
title: Settings
description: Branch specific settings
position: 2.4
right_code: |
  ~~~groovy
    project("SIMPLEPROJECT2") {
        name "Simple project"

        plan("SIMPLEPLAN") {
            name "Simple plan"
            description "this is a simple plan"
            enabled true

            branches {
                branch("develop") {
                    description "my developer branch"
                    enabled true
                    cleanupAutomatically true
                }
            }
        }
    }
  ~~~
  {: title="DSL" }

---
