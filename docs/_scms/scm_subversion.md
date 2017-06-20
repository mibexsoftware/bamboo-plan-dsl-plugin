---
title: Subversion
position: 1.5
right_code: |
  ~~~groovy
  scm {
      subversion(name: 'mySvn') {
          repositoryUrl 'http://svn.red-bean.com/repos/test'
          passwordAuth {
              userName 'admin'
              password 'pw'
          }
          advancedOptions {
              detectChangesInExternals true
              useSvnExport true
              enableCommitIsolation true
              autoDetectRootUrlForBranches false
              branchesRootUrl '/branches'
              autoDetectRootUrlForTags false
              tagRootUrl '/tags'
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
    - !subversion
      repositoryUrl: http://svn.red-bean.com/repos/test
      userName: admin
      name: mySvn
      authType: !password
        userName: admin
        password: pw
      advancedOptions:
        detectChangesInExternals: true
        useSvnExport: true
        enableCommitIsolation: true
        autoDetectRootUrlForBranches: false
        branchesRootUrl: /branches
        autoDetectRootUrlForTags: false
        tagRootUrl: /tags
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
A definition for Subversion repositories.