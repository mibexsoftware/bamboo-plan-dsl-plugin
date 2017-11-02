package dsls.projects

project(name: 'Simple project', key: 'SIMPLEPROJECT') {
    permissions {
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