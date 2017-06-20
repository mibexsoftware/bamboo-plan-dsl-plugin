---
title: Bitbucket Server trigger
position: 1.1
right_code: |
  ~~~groovy
  triggers {
      bitbucketServerRepositoryTriggered {
          description 'run when new code'
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  triggers:
    - !bitbucketServerTrigger
      description: run when new code
  ~~~
  {: title="YAML" }
---
Bitbucket Server triggers the build when changes are committed.