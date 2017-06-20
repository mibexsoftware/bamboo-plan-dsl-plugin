---
title: Responsible users notification
position: 1.7
right_code: |
  ~~~groovy
  notifications {
    responsibleUsers(event: NotificationEvent.ALL_JOBS_COMPLETED) {
    }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  notifications:
    - !responsibleUsers
       event: !event ALL_JOBS_COMPLETED
  ~~~
  {: title="YAML" }
---
Notify responsible users.