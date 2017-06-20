---
title: Triggers
description: Branch specific triggers
position: 2.4
right_code: |
  ~~~groovy
  branches {
      triggers(NewPlanBranchesTriggerType.RUN_NEW_PLAN_BRANCHES_MANUALLY)
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  branches:
    triggers: !newPlanBranchesTriggerType RUN_NEW_PLAN_BRANCHES_MANUALLY
  ~~~
  {: title="YAML" }
---
This defines how new plan branches should be triggered.
