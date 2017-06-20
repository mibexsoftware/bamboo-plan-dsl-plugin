---
title: User notification
position: 1.5
right_code: |
  ~~~groovy
  notifications {
    user(event: NotificationEvent.FAILED_JOBS_AND_FIRST_SUCCESSFUL) {
        user 'bob'
    }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  notifications:
    - !user
      event: !event FAILED_JOBS_AND_FIRST_SUCCESSFUL
      user: bob
  ~~~
  {: title="YAML" }
---
Notify individual Bamboo users.