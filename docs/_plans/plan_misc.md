---
title: Miscellaneous
position: 1.2
right_code: |
  ~~~ groovy
  plan(key: 'SIMPLEPLAN', name: 'Simple plan') {
      miscellaneous {
          expire {
              expireBuildResults true
              expireBuildArtifacts false
              expireBuildLogs true
              expireAfter 10, TimeUnit.WEEKS
              minimumBuildsToKeep 12
              keepBuildsWithTheFollowingLabels 'DONOTDELETE', 'IMPORTANT'
          }

          configure(
              'custom.ruby-config-runtime': 'SYSTEM 2.0.0-p648@default',
              'custom.ruby-config-environmentVariables': 'SOME_VAR="-D123 -R345"'
          )
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  plans:
    - key: SIMPLEPLAN
      name: Simple plan
      miscellaneous:
          expirationDetails:
            keepBuildsWithLabels:
              - DONOTDELETE
              - IMPORTANT
            expireAfter: 10
            expireTimeUnit: !timeUnit WEEKS
            minimumBuildsToKeep: 12
            expireBuildResults: true
            expireBuildArtifacts: false
            expireBuildLogs: true
            customSettings:
              custom.ruby-config-runtime: SYSTEM 2.0.0-p648@default
              custom.ruby-config-environmentVariables: SOME_VAR="-D123 -R345"

  ~~~
  {: title="YAML" } 
---
Specifies the miscellaneous options for this plan.