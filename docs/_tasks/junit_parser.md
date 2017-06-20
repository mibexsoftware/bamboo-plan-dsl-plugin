---
title: JUnit Parser
position: 2.3
right_code: |
  ~~~groovy
  tasks {
      junitParser {
          description 'parse test results'
          testResultsDirectory 'test-reports/'
          pickUpTestResultsCreatedOutsideOfBuild()
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  tasks:
    - !junitParser
      description parse test results
      testResultsDirectory: test-reports/
      pickUpTestResultsCreatedOutsideOfBuild: true
  ~~~
  {: title="YAML" }
---
A task to parse and display JUnit test results.