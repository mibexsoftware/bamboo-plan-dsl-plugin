---
title: Project Permissions
position: 1.1
right_code: |
  ~~~ groovy
  project(key: 'PROJECTKEY', name: 'my project') {
      projectPermissions {
          user(name: 'diego') {
              permissionTypes PermissionType.CREATE, PermissionType.ADMIN
          }
          group(name: 'devops') {
              permissionTypes PermissionType.CREATE
          }
          other(type: OtherUserType.LOGGED_IN_USERS) {
              permissionTypes PermissionType.CREATE
          }
      }   
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  project:
    key: PROJECTKEY
    name: my project
    projectPermissions:
      user:
        diego:
          - !permission ADMIN
          - !permission CREATE        
      group:
        devops: !permission CREATE
      other:
        !userType LOGGED_IN_USERS: !permission CREATE                     
  ~~~
  {: title="YAML" }

---

Project permissions allow you to control access to project permissions and settings. 
