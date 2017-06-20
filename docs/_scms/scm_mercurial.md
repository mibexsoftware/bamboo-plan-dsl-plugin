---
title: Mercurial
position: 1.6
right_code: |
  ~~~groovy
  scm {
      mercurial(name: 'myHg') {
          repositoryUrl 'http://hg.red-bean.com/repos/test'
          branch 'master'
          defaultMercurialCredentials {}
          advancedOptions {
              enableCommitIsolation true
              commandTimeoutInMinutes 180
              verboseLogs true
              disableRepositoryCaching false
              quietPeriod {
                  waitTimeInSeconds 120
                  maximumRetries 3
              }
              includeExcludeFiles(MatchType.EXCLUDE_ALL_MATCHING_CHANGES) {
                  filePattern 'exe'
              }
              excludeChangesetsRegex 'FIXES .*'
              webRepository {
                  bitbucket {}
              }
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  scm:
    - !mercurial
      name: myHg
      repositoryUrl: http://hg.red-bean.com/repos/test
      branch: master
      authType: !defaultMercurialAuth
      advancedOptions:
        enableCommitIsolation: true
        commandTimeoutInMinutes: 180
        verboseLogs: true
        disableRepositoryCaching: false
        quietPeriod:
          waitTimeInSeconds: 120
          maximumRetries: 3
        includeExcludeFiles:
          matchType: !matchType EXCLUDE_ALL_MATCHING_CHANGES
          filePattern: exe
        excludeChangesetsRegex: 'FIXES .*'
        webRepository:
         type: !bitbucketWeb
  ~~~
  {: title="YAML" }
---
A definition for Mercurial repositories.