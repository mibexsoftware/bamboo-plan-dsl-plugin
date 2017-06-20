---
title: Clean Working Directory
position: 1.5
right_code: |
  ~~~groovy
  tasks {
      cleanWorkingDirectory() {
          description 'Clean the working directory'
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  tasks:
    - !cleanWorkingDir
      isFinal: true
  ~~~
  {: title="YAML" }
---
A task to clean the working directory of a build job.