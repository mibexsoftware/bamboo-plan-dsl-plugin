---
title: Notifications
position: 1.0
right_code: |
  ~~~groovy
  plan(key: 'PLAN1KEY', name: 'my plan 1') {
    notifications {
    }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  plans:
    - key: MYPLAN
      name: my plan
      notifications:
      # Plan notifications
  ~~~
  {: title="YAML" }
---
Notifications can be specified on the plan and deployment project level.

The following notification types are supported by the plug-in:

  - [HipChat](#hipchat)
  - [E-Mail](#email)
  - [Stash legacy](#stashLegacy)
  - [Group](#group)
  - [User](#user)
  - [IM address](#imAddress)
  - [Responsible users](#responsibleUsers)
  - [Committers](#committers)
  - [Watchers](#watchers)

With the [custom](#custom) syntax you can also use plug-in provided notification types not built-in to Bamboo.
