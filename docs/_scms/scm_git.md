---
title: Git
position: 1.3
right_code: |
  ~~~groovy
  scm {
      git(name: 'myGitRepo') {
          url "http://localhost:7990/bitbucket/scm/project_1/java-maven-simple.git"
          branch "master"
          sharedCredentialsPasswordAuth "sharedpw"
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
                  filePattern "*.exe"
              }
              excludeChangesetsRegex "FIXES .*"
              webRepository {
                  fisheye {
                      url "http://localhost:7990"
                      repositoryPath "a/b/c"
                      repositoryName "d"
                  }
              }
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  scm:
    - !git
        name: myGitRepo
        url: http://localhost:7990/bitbucket/scm/project_1/java-maven-simple.git
        branch: master
        authType: !sharedCredentials
          sharedCredentialsType: !sharedCredentialsType USERNAMEPW
          name: sharedpw
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
A definition for plain Git repositories.