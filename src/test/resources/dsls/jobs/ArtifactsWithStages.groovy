package dsls.jobs

project("ARTIFACTS3") {
    name "artifacts test3"

    plan("ARTIFACTS3") {
        name "artifacts test3"
        description "this was a simple plan"
        enabled true

        scm {
            bitbucketCloud(name: "myBitbucketGitRepo") {
                git {
                }
                repoSlug "mrueegg/mvn-sub-modules"
                branch "develop"
                passwordAuth {
                    userName "mrueegg"
                }
            }
        }

        stage("simple stage") {
            description "this is simple stage"
            manual false

            job("SIMPLEJOB1") {
                name "Simple job 1"
                description "This was a simple job"
                enabled true

                tasks {
                    checkout() {
                        description 'Checkout default repository'
                        forceCleanBuild()

                        repository('myBitbucketGitRepo') {}
                    }
                    maven3x(goal: 'package') {
                        executable 'maven323'
                        buildJdk 'jdk8'
                        workingSubDirectory 'multimod/src'
                    }
                }

                artifacts {
                    definition(name: "my JAR2", copyPattern: "**/*.jar") {
                        location "target"
                        shared true
                    }
                }

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
                    artifactDownload() {
                        description("Download release content")
                        artifact("my JAR2") {
                              destinationPath("doc")
                        }
                        allArtifacts {
                            destinationPath("doc")
                        }
                    }
                    script() {
                        description("Unzip Artifact with RAMLs and more")
                        inline {
                            scriptBody 'unzip *.jar'
                            workingSubDirectory("doc")
                            interpreter ScriptInterpreter.LEGACY_SH_BAT
                        }
                    }
                }
            }
        }
    }
}