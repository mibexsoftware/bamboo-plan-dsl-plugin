---
title: Plan Permissions
position: 1.2
right_code: |
  ~~~ groovy
  project(key: 'PROJECTKEY', name: 'my project') {
      planPermissions {
          user(name: 'paul') {
              permissionTypes PermissionType.ADMIN
          }
          group(name: 'mgmt') {
              permissionTypes PermissionType.EDIT
          }
          other(type: OtherUserType.LOGGED_IN_USERS) {
              permissionTypes PermissionType.ADMIN, PermissionType.EDIT
          }
          other(type: OtherUserType.ANONYMOUS_USERS) {
              permissionTypes PermissionType.VIEW
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  project:
    key: PROJECTKEY
    name: my project
    planPermissions:
      user:
        paul: !permission ADMIN
      group:
        mgmt: !permission EDIT
      other:
        !userType LOGGED_IN_USERS:
          - !permission ADMIN        
          - !permission EDIT     
        !userType ANONYMOUS_USERS:  !permission VIEW                     
  ~~~
  {: title="YAML" }

---

Build plan permissions allow a user to control access to the functions of the build plan. These include viewing, 
editing, building, cloning and administering a build plan.
