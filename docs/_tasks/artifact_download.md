---
title: Artifact Download
position: 1.6
right_code: |
  ~~~groovy
  tasks {
      artifactDownload() {
          description 'Download release content'
          artifact(name: 'my JAR2') {
              destinationPath 'doc'
              sourcePlanKey 'MYPLAN'
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  tasks:
    - !artifactDownload
      description Download release content
      artifacts:
      - name: my JAR2
        destinationPath: doc
        sourcePlanKey: MYPLAN
  ~~~
  {: title="YAML" }
---
A task to copy a Bamboo shared artifact to an agent working directory.