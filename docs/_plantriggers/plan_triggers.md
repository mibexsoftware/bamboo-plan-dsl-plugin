---
title: Plan triggers
position: 1.0
right_code: |
  ~~~groovy
  plan(key: 'PLAN1KEY', name: 'my plan 1') {
    triggers {
    }
    branches {
      branch(name: 'develop') {
        triggers {
        }
      }
    }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  plans:
    - key: MYPLAN
      name: my plan
      triggers:
      # Plan triggers
      branches:
        - name: develop
        triggers:
          # Plan branch triggers
  ~~~
  {: title="YAML" }
---
Plan triggers can be installed on plans and plan branches.

The following trigger types are supported by the plug-in:

  - [Bitbucket Server trigger](#bbs_trigger)
  - [Scheduled trigger](#scheduled_trigger)
  - [Manual trigger](#manual_trigger)
  - [Polling trigger](#polling_trigger)
  - [Remote trigger](#remote_trigger)
  - [Once a day trigger](#once_a_day_trigger)
