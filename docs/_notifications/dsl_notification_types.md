---
title: Notification Types
position: 1.0
description: Example Not working!!!
right_code: |
  ~~~groovy
  notifications {
      hipchat(event: NotificationEvent.ALL_BUILDS_COMPLETED, 
              apiToken: env('my.apitoken'),
              room: env('my.room')) {
          notifyParticipants true
      }
  }
  ~~~
  {: title="DSL" }

---
Notifications can be specified on the plan and deployment project level. The definitions are wrapped in the `notifications` block. Here's an example for a HipChat notification:

The following notification types are supported by the DSL:

~~~groovy
void hipchat(NotificationEvent event, @DelegatesTo(HipChatNotification) Closure closure)

void email(NotificationEvent event, @DelegatesTo(EmailNotification) Closure closure)

void stashLegacy(NotificationEvent event, @DelegatesTo(StashLegacyNotification) Closure closure) 

void group(NotificationEvent event, @DelegatesTo(GroupNotification) Closure closure)

void user(NotificationEvent event, @DelegatesTo(UserNotification) Closure closure)

void imAddress(NotificationEvent event, @DelegatesTo(ImAddressNotification) Closure closure)

void responsibleUsers(NotificationEvent event, @DelegatesTo(ResponsibleUsersNotification) Closure closure)

void committers(NotificationEvent event, @DelegatesTo(CommittersNotification) Closure closure)

void watchers(NotificationEvent event, @DelegatesTo(WatchersNotification) Closure closure)

void custom(NotificationEvent event, String pluginKey, @DelegatesTo(CustomNotification) Closure closure)
~~~

For the individual notification parameters and their meaning, please consult the JavaDoc comments.

