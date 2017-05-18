---
title: Two Stages
position: 1.1
right_code: |
  ~~~ groovy
  project("SIMPLEPROJECT", "Renamed project") {

      plan(key: "SIMPLEPLAN", name: "Renamed plan") {
          description "this was a simple plan"
          enabled false

          scm {
              git("myrepo") {
                  url "http://localhost:7990/bitbucket/scm/project_1/java-maven-simple.git"
                  branch "master"
                  passwordAuth {
                      userName "admin"
                      password env('git.admin.password')
                  }
              }
          }

          stage(name: "first stage") {
              description "this was a simple stage"
              manual true

              job("SIMPLEJOB") {
                  description "This is a simple job"
                  enabled

                  tasks {
                      script('echo hello world') {

                      }
                  }
              }
          }

          stage("second stage") {
              description "this was a simple stage"
              manual
          }
      }
  }
  ~~~
  {: title="DSL" }

---
