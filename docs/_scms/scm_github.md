---
title: GitHub
position: 1.4
right_code: |
  ~~~groovy
  scm {
      github(name: 'myGithubRepo') {
          repoSlug 'test/HelloWorld'
          branch 'master'
          passwordAuth {
              userName 'myUser'
          }
          advancedOptions {
              useShallowClones true
              enableRepositoryCachingOnRemoteAgents true
              useSubmodules true
              commandTimeoutInMinutes 20
              verboseLogs true
              fetchWholeRepository true
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
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  scm:
    - !github
      name: myGithubRepo
      repoSlug: test/HelloWorld
      branch: master
      authType: !password
        userName: myUser
      advancedOptions:
        useShallowClones: true
        useSubmodules: true
        commandTimeoutInMinutes: 20
        verboseLogs: true
        fetchWholeRepository: true
        quietPeriod:
          waitTimeInSeconds: 120
          maximumRetries: 3
        includeExcludeFiles:
          matchType: !matchType EXCLUDE_ALL_MATCHING_CHANGES
        filePattern: '*.exe'
        excludeChangesetsRegex: 'FIXES .*'
        webRepository:
          type: !fisheyeWeb
            url: http://localhost:7990
            repositoryPath: a/b/c
            repositoryName: d
  ~~~
  {: title="YAML" }
---
A definition for GitHub repositories.