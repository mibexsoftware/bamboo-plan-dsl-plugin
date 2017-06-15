---
title: SCP
position: 2.4
right_code: |
  ~~~groovy
  tasks {
      scp(host: 'localhost', userName: 'bob') {
          description 'Ship it to remote server'
          passwordAuth {
              password env('MY_PASSWORD_VARIABLE')
          }
          artifactByLocalPath(localPath: '*.zip,*.jar') {
              useAntPatternsToSelectFiles()
          }
          remotePath 'a/b'
          advancedOptions {
              hostFingerprint 'test'
              port 22
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
Copy files to a remote server using SCP.