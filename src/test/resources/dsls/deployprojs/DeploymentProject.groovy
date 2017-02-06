package dsls.deployprojs

project("SIMPLEPROJECT") {
    name "simple project"

    plan("SIMPLEPLAN") {
        name "simple plan"

        deploymentProject("dp1") {
            description "desc1"
            useCustomPlanBranch "develop"

            environment("env1") {
                description "desc"
            }

            releaseVersioning(nextReleaseVersion: '1.0-m1') {
                autoIncrement()
                variables "test1", "test2"
            }

            permissions {
                user(name: 'diego') {
                    permissionTypes PermissionType.VIEW
                }

                group(name: 'devops') {
                    permissionTypes PermissionType.VIEW
                }

                other(type: OtherUserType.LOGGED_IN_USERS) {
                    permissionTypes PermissionType.VIEW, PermissionType.EDIT
                }
            }
        }

        deploymentProject(name: "dp2") {
            description "desc1"
            useCustomPlanBranch "release"

            environment(name: "env1") {
                description "desc1"
            }
            environment("env2") {
                description "desc2"

                variables {
                    variable "key1", "value1"
                    variable "key2", "value2"
                }

                notifications {
                    hipchat(event: EnvironmentNotificationEvent.DEPLOYMENT_FAILED, apiToken: "XXX", room: "MyRoom") {
                        notifyParticipants true
                    }
                }

                permissions {
                    user(name: 'diego') {
                        permissionTypes PermissionType.VIEW
                    }

                    group(name: 'devops') {
                        permissionTypes PermissionType.VIEW, PermissionType.DEPLOY
                    }

                    other(type: OtherUserType.LOGGED_IN_USERS) {
                        permissionTypes PermissionType.VIEW, PermissionType.EDIT
                    }
                }
            }

            releaseVersioning('1.0-${bamboo.buildNumber}') {
                variables "test3", "test4"
            }
        }

    }
}