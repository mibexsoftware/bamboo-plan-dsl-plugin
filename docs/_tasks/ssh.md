---
title: SSH
position: 2.5
right_code: |
  ~~~groovy
  tasks {
      ssh(host: 'localhost', userName: 'bob') {
          description 'show dir on remote server'
          keyWithoutPassphrase {
              privateKey env('MY_PRIVATE_KEY')
              enableSshCompression()
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
  tasks:
    - !ssh
      host: localhost
      userName: bob
      description: show dir on remote server
      authType: !sshWithoutPassphraseAuth
        privateKey: !env MY_PRIVATE_KEY
        enableSshCompression: true
      command: ls -l
      advancedOptions:
        hostFingerprint: test
        port: 22
  ~~~
  {: title="YAML" }
---
A task to run a remote command over SSH.