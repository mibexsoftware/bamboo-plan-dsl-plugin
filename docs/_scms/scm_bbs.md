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
              enableLfsSupport true
              quietPeriod {
                  waitTimeInSeconds 10
                  maximumRetries 15
              }
              includeExcludeFiles(MatchType.EXCLUDE_ALL_MATCHING_CHANGES) {
                  filePattern '*.exe'
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
    - !bitbucketServer
      projectKey: project_1
      repoSlug: rep_1
      branch: develop
      serverName: bitbucketServer
      repositoryUrl: ssh://git@localhost:7999/project_1/rep_1.git
      advancedOptions:
        enableRepositoryCachingOnRemoteAgents: true
        useShallowClones: true
        useSubmodules: false
        commandTimeoutInMinutes: 20
        verboseLogs: true
        fetchWholeRepository: false
        enableLfsSupport: true
        quietPeriod:
          waitTimeInSeconds: 10
          maximumRetries: 15
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
A definition for Bitbucket Server repositories.