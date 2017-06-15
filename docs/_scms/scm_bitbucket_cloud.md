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
  ~~~
  {: title="YAML" }
---
An example of a Bitbucket Cloud repository definition.