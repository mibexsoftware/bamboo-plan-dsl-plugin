---
title: E-Mail
position: 1.2
right_code: |
  ~~~groovy
  notifications {
    email(event: NotificationEvent.FIRST_FAILED_JOB_FOR_PLAN) {
        email 'your@mail.com'
    }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  notifications:
    - !email
      address: your@mail.com
      event: !event FIRST_FAILED_JOB_FOR_PLAN
  ~~~
  {: title="YAML" }
---
Notify users by e-mail.
