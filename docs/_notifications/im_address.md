---
title: IM address notification
position: 1.6
right_code: |
  ~~~groovy
  notifications {
    imAddress(event: NotificationEvent.FAILED_JOBS_AND_FIRST_SUCCESSFUL) {
        instantMessagingAddress 'bill@chat.com'
    }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  notifications:
    - !imAddress
      event: !event FAILED_JOBS_AND_FIRST_SUCCESSFUL
      instantMessagingAddress: bill@chat.com
  ~~~
  {: title="YAML" }
---
Notify users by their instant messanger address.