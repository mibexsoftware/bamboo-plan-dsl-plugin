---
title: Requirements
position: 1.4
right_code: |
  ~~~ groovy
  job(key: 'PACKAGE', name: 'Packages the software') {
      requirements {
          requirement(capabilityKey: 'system.builder.mvn3.maven323',
                      matchType: matches('[A-Z0-9]*')) {
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  ~~~
  {: title="YAML" } 
---
Defines the requirements for this job.
