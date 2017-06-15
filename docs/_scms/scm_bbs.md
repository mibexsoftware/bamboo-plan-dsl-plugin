---
title: Bitbucket Server
position: 1.2
right_code: |
  ~~~groovy
  scm {
      bitbucketServer(name: 'myBitbucketServerRepo') {
          projectKey 'project_1'
          repoSlug 'rep_1'
          branch 'develop'
          serverName 'bitbucketServer'
          repositoryUrl 'ssh://git@localhost:7999/project_1/rep_1.git'

          advancedOptions {
              enableRepositoryCachingOnRemoteAgents true
              useShallowClones true
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
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
An example of a Bitbucket Server repository definition.