---
title: Permissions
description: Not working!!
position: 1.0
right_code: |
  ~~~groovy
   permissions {
      user(name: 'diego') {
          permissionTypes PermissionType.ADMIN
      }

      group(name: 'devops') {
          permissionTypes PermissionType.VIEW, PermissionType.BUILD
      }

      other(type: OtherUserType.ANONYMOUS_USERS) {
          permissionTypes PermissionType.VIEW, PermissionType.ADMIN
      }
  }
  ~~~
  {: title="DSL" }

---
The `permissions` block can be used to configure permissions on plan, deployment project and environment level.

Permissions apply to users, groups and special user groups like logged and anonymous users. 
The permission types correspond to the ones shown in the Bamboo UI.
