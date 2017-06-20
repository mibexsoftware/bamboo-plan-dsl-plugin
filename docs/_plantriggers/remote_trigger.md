---
title: Remote trigger
position: 1.5
right_code: |
  ~~~groovy
  triggers {
      remote() {
          description 'my remote trigger'
          repositories 'myrepo'
          ipAddresses '127.0.0.1', '192.168.0.1'
          onlyRunIfOtherPlansArePassing {
              planKeys 'PROJ-PLAN1', 'PROJ-PLAN3', 'PROJ-PLAN5'
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  triggers:
    - !remote
      description: my remote trigger
      repositories: [myrepo]
      ipAddresses: [127.0.0.1, 192.168.0.1]
      onlyRunIfOtherPlansArePassing:
        planKeys: [PROJ-PLAN1, PROJ-PLAN3, PROJ-PLAN5]
  ~~~
  {: title="YAML" }
---
Repository triggers the build when changes are committed.