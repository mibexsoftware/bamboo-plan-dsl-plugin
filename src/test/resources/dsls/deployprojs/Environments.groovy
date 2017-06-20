package dsls.deployprojs

import ch.mibex.bamboo.plandsl.dsl.notifications.Notifications

project("DPPROJ") {
    name "deployment project with custom branch"

    plan("DPPROJ") {
        name "deployment project with custom branch"

        branches {
            branch("develop") {
                description "my developer branch"
                enabled true
                cleanupAutomatically true
            }
        }

        stage("another stage") {
            description "this is simple stage"
            manual false

            job("SIMPLEJOB2") {
                name "Simple job 2"
                description "This was a simple job"
                enabled true

                tasks {
                    script() {
                        description 'echo hello world'
                        inline {
                            interpreter ScriptInterpreter.LEGACY_SH_BAT
                            scriptBody 'echo "Hello World"'
                        }
                    }
                }
            }
        }

        scm {
            bitbucketCloud("myBitbucketGitRepo") {
                git {
                    advancedOptions {
                        useShallowClones true
                        useSubmodules true
                        commandTimeoutInMinutes 20
                        verboseLogs true
                        fetchWholeRepository true
                        quietPeriod {
                            waitTimeInSeconds 120
                            maximumRetries 3
                        }
                        includeExcludeFiles(MatchType.EXCLUDE_ALL_MATCHING_CHANGES) {
                            filePattern "exe"
                        }
                        excludeChangesetsRegex "FIXES .*"
                        webRepository {
                            fisheye {
                                url "http://localhost:7990"
                                repositoryPath "a/b/c"
                                repositoryName "d"
                            }
                        }
                    }
                }
                repoSlug "project_1/java-maven-simple"
                branch "master"
                passwordAuth {
                    userName "admin"
                    password "pw"
                }
            }
        }

        deploymentProject("name") {
            description "desc"
            useCustomPlanBranch "develop"

            environment(name: "env1", id: 59998211) {
            }

            environment(name: "env2", id: 59998212) {
            }

            environment("env") {
                description "env desc"

                tasks {
                    command("run command") {
                        enabled true
                        isFinal true
                        workingSubDirectory "."
                        argument "-n"
                        environmentVariables "what=EVER"
                        executable "atlas-clean"
                    }
                }

                triggers {
                    scheduledCron("0 0 0 ? * *") {
                    }
                    afterSuccessfulStage() {
                        customPlanBranchName("develop")
                        planStageToTriggerThisDeployment("another stage")
                    }
                    afterSuccessfulBuildPlan() {
                        customPlanBranchName("develop")
                    }
                    afterSuccessDeployment(triggeringEnvironment: 'PROD') {
                    }
                }

                notifications {
                    email(event: Notifications.NotificationEvent.DEPLOYMENT_FAILED) {
                        email: 'your@mail.com'
                    }
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

                variables {
                    variable "key1", "value1"
                    variable "key2", "value2"
                }

//                agentAssignments {
//                    requirements {
//                        requirement(capabilityKey: 'system.builder.gradle.Gradle 2.2',
//                                    matchType: equalsTo("2.2")) {
//                        }
//                    }
//                    dedicatedAgents {
//                        dedicatedAgent(name: '') {
//                        }
//                    }
//                }
            }
        }

    }
}