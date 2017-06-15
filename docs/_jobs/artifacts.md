---
title: Artifacts
position: 1.2
right_code: |
  ~~~ groovy
  job(key: 'BUILD', name: 'Builds the software') {
      artifacts {
          definition(name: 'my JAR', copyPattern: '**/*.jar') {
              location 'target'
              shared true
          }
          // more artifact definitions
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  ~~~
  {: title="YAML" } 
---
Defines the artifact(s) for this job.