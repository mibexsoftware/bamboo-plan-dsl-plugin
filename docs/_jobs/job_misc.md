---
title: Miscellaneous
position: 1.5
right_code: |
  ~~~ groovy
  job(key: 'TEST', name: 'Tests the software') {
      miscellaneous {
          cleanWorkingDirectoryAfterEachBuild()
          overrideDefaultHangingBuildDetectionCriteria {
              buildTimeMultiplier 2.5
              logQuietTimeInMinutes 10
              buildQueueTimeoutInMinutes 60
          }
          nCoverOutputWillBeProduced {
              nCoverXmlDirectory 'a/b/c'
          }
          cloverCodeCoverage {
              automaticallyIntegrateCloverIntoBuild(cloverLicense: 'LICENSE') {
                  generateCloverHistoricalReport()
                  generateJSONReport()
              }
          } 
          patternMatchLabelling {
              regexPattern '[a-z]+'
              labels 'test'
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  jobs:
    - key: PACKAGE
      name: Packages the software
      miscellaneous:
        cleanWorkingDirectoryAfterEachBuild: true
        buildHungOptions:
          buildTimeMultiplier: 2.5
          logQuietTimeInMinutes: 10
          buildQueueTimeoutInMinutes: 60
        nCoverOutput:
          nCoverXmlDirectory: a/b/c
        cloverCodeCoverage:
          cloverLicense: 'LICENSE'
          cloverOptions:
            generateCloverHistoricalReport: true
            generateJSONReport: false
          integrationOptions: !integration AUTOMATICALLY_INTEGRATE_CLOVER_INTO_BUILD
        patternMatchLabelling:
          regexPattern: [a-z]+
          labels: test
  ~~~
  {: title="YAML" } 
---
Defines the miscellaneous options for this [job](#jobs). Here you can configure the build hung options,
NCover and Clover settings.