---
title: Jobs
position: 1.0
right_code: |
  ~~~ groovy
  stage(name: 'package') {
      job(key: 'TEST', name: 'Tests the software') {
          description 'This is the job description'
          enabled true
          tasks {
          }
          artifacts {
          }
          requirements {
          }   
          miscellaneous {
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  stages:
    - name: package
      jobs:
        - key: TEST
          name: Tests the software
          description: This is the job description
          enabled: true
          tasks:
          artifactDependencies:
          artifactDefinitions:
          requirements:
          miscellaneous:
  ~~~
  {: title="YAML" }  
---
A build job has a key, a name, a description, an enabled flag, a list of tasks, artifacts, requirements and 
miscellaneous options. 
