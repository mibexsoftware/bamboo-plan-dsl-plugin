---
title: Artifact Dependencies
position: 1.2
right_code: |
  ~~~ groovy
    project("SIMPLEPROJECT") {
        name "Renamed project"

        plan("SIMPLEPLAN") {
            name "Renamed plan"
            description "this was a simple plan"
            enabled false

            stage("simple stage") {
                description "this is simple stage"
                manual false

                job("SIMPLEJOB1") {
                    name "Simple job 1"
                    description "This was a simple job"
                    enabled false

                    tasks {
                        script('echo hello world') {
                        }
                    }

                    artifacts {
                        definition("my JAR") {
                            location "target"
                            copyPattern "**/*.jar"
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
                    enabled false

                    tasks {
                        script('echo hello world') {
                        }
                    }

                    artifacts {
                        dependency(name: "my JAR") {
                            destinationDirectory "target"
                        }
                    }
                }
            }
        }
    }
  ~~~
  {: title="DSL" }

---


