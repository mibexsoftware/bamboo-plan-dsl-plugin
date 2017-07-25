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
          requirement(capabilityKey: 'system.builder.msbuild.MSBuild v4.0 (64bit)',
                      matchType: exists()) {
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  jobs:
    - key: PACKAGE
      name: Packages the software
      requirements:
        - capabilityKey: system.builder.mvn3.maven323
          matchType: !matches
            matchValue: '[A-Z0-9]*'
        - capabilityKey: system.builder.msbuild.MSBuild v4.0 (64bit)
          matchType: !exists
  ~~~
  {: title="YAML" } 
---
Defines the requirements for this [job](#jobs).
