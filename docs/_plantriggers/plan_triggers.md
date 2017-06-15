---
title: Plan triggers
position: 1.0
right_code: |
  ~~~groovy
  triggers {
      scheduled() {
          description 'run every day at noon'
          cron '0 0 0 ? * *'
              onlyRunIfOtherPlansArePassing {
                  planKeys 'OTHER-PLAN'
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
Plan triggers can be installed on plans and plan branches. The definitions are wrapped in 
the `triggers` block. Here's an example for a scheduled cron-based trigger.

The following trigger types are supported by the DSL:

  - [Bitbucket Server trigger](#bbs_trigger)
  - [Scheduled trigger](#scheduled_trigger)
  - [Manual trigger](#manual_trigger)
  - [Polling trigger](#polling_trigger)
  - [Remote trigger](#remote_trigger)
  - [Once a day trigger](#once_a_day_trigger)
