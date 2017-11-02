package dsls.projects

project(name: 'Simple project', key: 'SIMPLEPROJECT') {
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