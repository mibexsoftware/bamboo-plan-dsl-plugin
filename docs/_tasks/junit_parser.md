---
title: JUnit Parser
position: 2.3
right_code: |
  ~~~groovy
  tasks {
      junitParser {
          description 'parse test results'
          testResultsDirectory 'test-reports/*.xml'
          pickUpTestResultsCreatedOutsideOfBuild()
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
Parses and displays JUnit test results.