---
title: Command
position: 1.2
right_code: |
  ~~~groovy
  tasks {
      command() {
          description 'my command'
          enabled true
          isFinal true
          workingSubDirectory 'mydir'
          argument '-n'
          environmentVariables 'what=EVER'
          executable 'atlas-clean'
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
A command task.