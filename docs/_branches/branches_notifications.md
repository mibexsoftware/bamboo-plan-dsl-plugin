---
title: Notifications
position: 2.3
right_code: |
  ~~~groovy
  branches {
      notifications(Branch.NotifyOnNewBranchesType.DO_NOT_SEND_NOTIFICATIONS)
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  branches:
    notifications: !notifyOnNewBranchesType DO_NOT_SEND_NOTIFICATIONS
  ~~~
  {: title="YAML" }
---
These notification preferences are applied to all new [plan branches](#branches).