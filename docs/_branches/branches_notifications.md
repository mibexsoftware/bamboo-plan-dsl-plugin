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
  ~~~
  {: title="YAML" }
---
These notification preferences are applied to all new plan branches.