---
title: Scheduling trigger
description: Branch specific scheduled trigger
position: 2.3
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
                    triggers {
                        scheduled {
                            cronExpression "0 15 10 * * ?"
                            onlyRunIfOtherPlansArePassing {
                                planKeys "SEED-SEED-JOB1", "SEED-SEED-JOB2"
                            }
                        }
                    }
                }
            }
        }
    }
  ~~~
  {: title="DSL" }

---
