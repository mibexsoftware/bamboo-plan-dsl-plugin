---
title: Artifact Dependencies
position: 1.3
right_code: |
  ~~~ groovy
  job(key: 'DEPLOY', name: 'Deploys the software') {
      artifacts {
          dependency(name: 'my JAR') {
              destinationDirectory 'deploy'
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  jobs:
    - key: DEPLOY
      name: Deploys the software
      artifactDependencies:
        - name: my JAR
          destinationDirectory: deploy
  ~~~
  {: title="YAML" } 
---
Defines the artifact dependencies for this job.


