---
title: SSH
position: 2.5
right_code: |
  ~~~groovy
  tasks {
      ssh(host: 'localhost', userName: 'bob') {
          description 'login to remote server'
          keyWithoutPassphrase {
              privateKey env('MY_PRIVATE_KEY')
          }
          command 'ls -l'
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
Run a remote command over SSH.