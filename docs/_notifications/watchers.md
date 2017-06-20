---
title: Watchers notification
position: 1.9
right_code: |
  ~~~groovy
  notifications {
    watchers(event: NotificationEvent.FIRST_FAILED_JOB_FOR_PLAN) {
    }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  notifications:
    - !watchers
      event: !event FIRST_FAILED_JOB_FOR_PLAN
  ~~~
  {: title="YAML" }
---
Notify users who have marked this build as their favourite.