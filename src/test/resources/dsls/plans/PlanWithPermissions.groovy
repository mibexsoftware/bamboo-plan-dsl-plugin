package dsls.plans

project("SIMPLEPROJECT") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

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
    }
}