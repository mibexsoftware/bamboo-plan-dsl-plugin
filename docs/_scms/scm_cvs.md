---
title: CVS
position: 1.7
right_code: |
  ~~~groovy
  scm {
      cvs(name: 'myCvsRepo') {
          cvsRoot 'http://localhost:7990/bitbucket/scm/project_1/java-maven-simple.cvs'
          quietPeriodInSeconds 60
          module 'test'
          moduleVersion CvsModuleVersion.HEAD
          passwordAuth {
              password 'pw'
          }
          advancedOptions {
              includeExcludeFiles(MatchType.INCLUDE_ONLY_MATCHING_CHANGES) {
                  filePattern 'bat'
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
    - !cvs
      name: myCvsRepo
      cvsRoot: http://localhost:7990/bitbucket/scm/project_1/java-maven-simple.cvs
      quietPeriodInSeconds: 60
      module: test
      moduleVersion: !cvsModuleVersion HEAD
      authType: !password
        password: pw
      advancedOptions:
        includeExcludeFiles:
          matchType: !matchType INCLUDE_ONLY_MATCHING_CHANGES
        filePattern: 'bat'
        excludeChangesetsRegex: 'FIXES .*'
        webRepository
          type: !fisheyeWeb
            url: http://localhost:7990
              repositoryPath: a/b/c
              repositoryName: d
  ~~~
  {: title="YAML" }
---
A definition for CVS repositories.