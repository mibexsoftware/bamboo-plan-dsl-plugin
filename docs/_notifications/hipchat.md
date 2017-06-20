---
title: HipChat
position: 1.1
right_code: |
  ~~~groovy
  notifications {
    hipchat(event: NotificationEvent.ALL_BUILDS_COMPLETED, apiToken: 'XXX', room: 'MyRoom') {
      notify true
    }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  notifications:
    - !hipchat
      apiToken: XXX
      room: MyRoom
      event: !event ALL_BUILDS_COMPLETED
      notify: true
  ~~~
  {: title="YAML" }
---
Notify users by HipChat.