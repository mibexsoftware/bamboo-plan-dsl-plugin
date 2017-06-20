---
title: Maven 3.x
position: 1.9
right_code: |
  ~~~groovy
  tasks {
      maven3x(goal: 'install') {
          description 'build plug-in'
          workingSubDirectory '.'
          executable 'maven323'
          buildJdk 'jdk8'
          environmentVariables 'what=EVER'
          withTests {
              testResultsDirectory 'tests/'
          }
          useMavenReturnCode false
          projectFile 'a/b'
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  tasks:
    - !maven3x
      goal: install
      description: build plug-in
      workingSubDirectory: .
      executable: maven323
      buildJdk: jdk8
      environmentVariables: what=EVER
      withTests:
        testResultsDirectory: tests/
      projectFile: a/b
      useMavenReturnCode: false
  ~~~
  {: title="YAML" }
---
A task to execute one or more Maven 3 goals as part of a build.