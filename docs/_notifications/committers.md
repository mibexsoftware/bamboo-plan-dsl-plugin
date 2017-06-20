---
title: Committers notification
position: 1.8
right_code: |
  ~~~groovy
  notifications {
    committers(event: NotificationEvent.CHANGE_OF_BUILD_STATUS) {
    }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  notifications:
    - !committers
      event: !event CHANGE_OF_BUILD_STATUS
  ~~~
  {: title="YAML" }
---
Notify users who have committed to the build.