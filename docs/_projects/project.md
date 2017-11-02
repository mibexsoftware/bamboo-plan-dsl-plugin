---
title: Project
position: 1.0
right_code: |
  ~~~ groovy
  project(key: 'PROJECTKEY', name: 'my project') {
      plan(key: 'PLAN1KEY', name: 'my plan 1') {
      }
      plan(key: 'PLAN2KEY', name: 'my plan 2') {
      }
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
      planPermissions {
          user(name: 'paul') {
              permissionTypes PermissionType.ADMIN
          }
          group(name: 'mgmt') {
              permissionTypes PermissionType.CREATE
          }
          other(type: OtherUserType.LOGGED_IN_USERS) {
              permissionTypes PermissionType.ADMIN, PermissionType.CREATE
          }
          other(type: OtherUserType.ANONYMOUS_USERS) {
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
    plans:
      - key: PLAN1KEY
        name: my plan 1
      - key: PLAN2KEY
        name: my plan 2
    projectPermissions:
      user:
        diego:
          - !permission ADMIN
          - !permission CREATE        
      group:
        devops: !permission CREATE
      other:
        !userType LOGGED_IN_USERS: !permission CREATE     
    planPermissions:
      user:
        paul: !permission ADMIN
      group:
        mgmt: !permission CREATE
      other:
        !userType LOGGED_IN_USERS:
          - !permission ADMIN        
          - !permission CREATE     
        !userType ANONYMOUS_USERS:  !permission CREATE                   
  ~~~
  {: title="YAML" }

---

A project contains of a collection of [plans](#plan). Each project has a key (which must consist of an uppercase
letter followed by one or more uppercase alphanumeric characters), a name and its build plans. Since Bamboo 6.2, it
can also have project and plan permissions for users and groups.
