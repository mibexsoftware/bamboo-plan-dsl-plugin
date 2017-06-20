---
title: Custom notification
position: 2.0
right_code: |
  ~~~groovy
  notifications {
    custom(event: NotificationEvent.AFTER_X_BUILD_FAILURES,
      pluginKey: 'ch.mibex.bamboo.smsnotification:smsnotification.recipient') {
      numberOfFailures 1
      configure(
              twilioAccountSid: 'twilioAccountSid',
              twilioAuthToken: 'twilioAuthToken',
              smsFromNumber: 'smsFromNumber',
              smsToNumber: 'smsToNumber'
      )
    }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  notifications:
    - !customNotification
      event: !event FAILED_BUILDS_AND_FIRST_SUCCESSFUL
      notificationRecipientType: ch.mibex.bamboo.smsnotification:smsnotification.recipient
      numberOfFailures: 1
      config:
        twilioAccountSid: [!env twilioAccountSid]
        twilioAuthToken: [!env twilioAuthToken]
        smsFromNumber: [!env smsFromNumber]
        smsToNumber: [!env smsToNumber]
  ~~~
  {: title="YAML" }
---
A custom (i.e., not built-in) notification.
