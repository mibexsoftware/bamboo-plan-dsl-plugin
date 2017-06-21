---
title: Stash Legacy
position: 1.3
right_code: |
  ~~~groovy
  notifications {
    stashLegacy(event: NotificationEvent.CHANGE_OF_JOB_STATUS) {
    }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  notifications:
    - !stashLegacy
      event: !event CHANGE_OF_JOB_STATUS
  ~~~
  {: title="YAML" }
---
This notification type is obsolete and should no longer be used. Bamboo notifies Bitbucket Server about
every build result automatically.