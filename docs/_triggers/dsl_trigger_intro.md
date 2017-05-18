---
title: Triggers
position: 1.0
description: Example not working!!!
right_code: |
  ~~~groovy
  triggers {
      scheduled() {
          description 'run every day at noon'
          cron '0 0 0 ? * *'
              onlyRunIfOtherPlansArePassing {
                  planKeys 'OTHER-PLAN'
              }
          }
      }
  ~~~
  {: title="DSL" }

---
Triggers can be specified on the plan, plan branch and deployment project level. The definitions are wrapped in the `triggers` block. Here's an example for a scheduled cron-based trigger.

The following trigger types are supported by the DSL:

~~~groovy
void scheduled(@DelegatesTo(ScheduledTrigger) Closure closure)

void bitbucketServerRepositoryTriggered(@DelegatesTo(BitbucketServerTrigger) Closure closure)

void scheduled(@DelegatesTo(ScheduledTrigger) Closure closure)

void manual()

void polling(@DelegatesTo(PollingTrigger) Closure closure)

void remote(@DelegatesTo(RemoteTrigger) Closure closure)

void onceAday(@DelegatesTo(OnceADayTrigger) Closure closure)
~~~
For the individual trigger parameters and their meaning, please consult the JavaDoc comments.


