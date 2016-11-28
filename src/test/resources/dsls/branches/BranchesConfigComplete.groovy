package dsls.branches

project("SIMPLEPROJECT2") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true


        stage("simple stage") {
            description "this is a simple stage"
            manual false

            job("SIMPLEJOB") {
                name "Simple job"
                description "This is a simple job"
                enabled true

                tasks {
                    script("say hello world") {
                        inline {
                            scriptBody 'echo "Hello world"'
                            interpreter ScriptInterpreter.LEGACY_SH_BAT
                        }
                    }
                }
            }

        }

        scm {
            bitbucketCloud(name: "myBitbucketGitRepo") {
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
                repoSlug "mrueegg/mvn-sub-modules"
                branch "develop"
                passwordAuth {
                    userName "admin"
                }
            }
        }

        branches {
            autoBranchManagement {
                newPlanBranchesForMatchingBranchNames("feature/*")
                deletePlanBranchesAfterDays(7)
                deleteInactivePlanBranchesAfterDays(14)
            }

            merging {
                gateKeeper {
                    checkoutBranch "SIMPLEPROJECT-SIMPLEPLAN"
                    pushEnabled true
                }
            }

            notifications {
                custom(NotificationConditions.AFTER_X_BUILD_FAILURES, "") {
                }
            }
            triggers(NewPlanBranchesTriggerType.RUN_NEW_PLAN_BRANCHES_MANUALLY)

            branch(name: "develop") {
                description "my developer branch"
                enabled true
                cleanupAutomatically true

                triggers {
//                    scheduled {
//                        cron("* * * * * * *")
//                        onlyRunIfOtherPlansArePassing {
//                            planKeys "SEED-SEED-JOB1"
//                        }
//                    }
//                    remote {
//                        repositories("myBitbucketGitRepo")
//                        ipAddresses("127.0.0.1,192.168.1.1")
//                    }
//                    onceAday {
//                        buildTime "12:00"
//                    }
//                    polling {
//                        scheduled {
//                            cron("* * * * * * *")
//                        }
//                        repositories("myBitbucketGitRepo")
//                    }
                    polling {
                        periodically {
                            pollingFrequencyInSecs 30
                        }
                        repositories("myBitbucketGitRepo")
                    }
                }

                merging {
                    branchUpdater {
                        mergeFromBranch "SIMPLEPROJECT2-SIMPLEPLAN0"
                        pushEnabled false
                    }
                }

                variables {
                    variable "name", "value"
                }

                notifications(NotifyOnNewBranchesType.USE_PLANS_NOTIFICATION_SETTINGS)
            }
        }

    }
}