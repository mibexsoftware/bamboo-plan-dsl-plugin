---
title: Permissions
position: 1.0
right_code: |
  ~~~groovy
   permissions {
      user(name: 'diego') {
          permissionTypes PermissionType.ADMIN
      }

      group(name: 'devops') {
          permissionTypes PermissionType.BUILD
      }

      other(type: OtherUserType.ANONYMOUS_USERS) {
          permissionTypes PermissionType.VIEW
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  permissions:
    user:
      diego: !permission ADMIN
    group:
      devops: !permission BUiLD
    other:
      !userType ANONYMOUS_USERS: !permission VIEW
  ~~~
  {: title="YAML" }
---

Can be used to configure permissions on [plan](#plan), [deployment project](#deployment_projects) and
[environment](#environments) level.

Permissions apply to users, groups and special user groups like logged-in and anonymous users.
The permission types correspond to the ones shown in the Bamboo UI.
