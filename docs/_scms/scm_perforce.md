---
title: Perforce
position: 1.7
right_code: |
  ~~~groovy
  scm {
      perforce(name: 'myPerforceRepo') {
          port '9091'
          client 'perforce'
          depotView '//perforce/workspace'
          environmentVariables 'P4CHARSET=\"utf8\"'
          letBambooManageWorkspace true
          useClientMapping true
          passwordAuth {
              userName 'admin'
              password 'pw'
          }
          advancedOptions {
              quietPeriod {
                  waitTimeInSeconds 120
                  maximumRetries 3
              }
              includeExcludeFiles(MatchType.EXCLUDE_ALL_MATCHING_CHANGES) {
                  filePattern 'exe'
              }
              excludeChangesetsRegex 'FIXES .*'
              webRepository {
                  fisheye {
                      url 'http://localhost:7990'
                      repositoryPath 'a/b/c'
                      repositoryName 'd'
                  }
              }
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  scm:
    - !perforce
      name: myPerforceRepo
      port: 9091
      client: perforce
      depotView: //perforce/workspace
      environmentVariables: P4CHARSET=utf8
      letBambooManageWorkspace: true
      useClientMapping: true
      passwordAuth: !password
        userName: admin
        password: pw
      advancedOptions:
        quietPeriod:
          waitTimeInSeconds: 120
          maximumRetries: 3
        includeExcludeFiles:
          matchType: !matchType EXCLUDE_ALL_MATCHING_CHANGES
          filePattern: exe
        excludeChangesetsRegex: 'FIXES .*'
        webRepository:
          type: !fisheyeWeb
            url: http://localhost:7990
            repositoryPath: a/b/c
            repositoryName: d
  ~~~
  {: title="YAML" }
---
A definition for Perforce repositories.