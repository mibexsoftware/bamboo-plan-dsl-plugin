---
title: Bitbucket Cloud
position: 1.1
right_code: |
  ~~~groovy
  scm {
      bitbucketCloud(name: 'myBitbucketGitRepo') {
          git { 
              advancedOptions {
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
          repoSlug 'project_1/java-maven-simple'
          branch 'develop'
          passwordAuth {
              userName 'admin'
              password 'pw'
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  scm:
    - !bitbucketCloud
      name: myBitbucketGitRepo
      advancedOptions:
        useShallowClones: true
        useSubmodules: false
        commandTimeoutInMinutes: 20
        verboseLogs: true
        fetchWholeRepository: false
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
        repoSlug: project_1/java-maven-simple
        branch: develop
        authType: !password
          userName: admin
          password: pw
  ~~~
  {: title="YAML" }
---
A definition for Bitbucket Cloud repositories.