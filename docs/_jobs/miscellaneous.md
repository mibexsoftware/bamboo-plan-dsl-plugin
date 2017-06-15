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
  ~~~
  {: title="YAML" } 
---
Defines the miscellaneous options for this job.