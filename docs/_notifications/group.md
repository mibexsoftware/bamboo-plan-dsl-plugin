---
title: Group notification
position: 1.4
right_code: |
  ~~~groovy
  notifications {
    group(event: NotificationEvent.CHANGE_OF_JOB_STATUS) {
        group 'mygroup'
    }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  notifications:
    - !group
      event: !event CHANGE_OF_JOB_STATUS
      group: mygroup
  ~~~
  {: title="YAML" }
---
Notify Bamboo user groups.