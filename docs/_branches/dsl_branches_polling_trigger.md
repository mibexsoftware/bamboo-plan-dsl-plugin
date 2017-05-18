---
title: Polling trigger
description: Branch specific polling trigger
position: 2.2
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
                        polling {
                            periodically {
                                pollingFrequencyInSecs 30
                            }
                            repositories("repo123", "repo456")
                        }
                    }
                }
            }
        }
    }
  ~~~
  {: title="DSL" }

---
