---
title: Environments
position: 1.1
right_code: |
  ~~~groovy
  deploymentProject(name: 'DP1', id: 1) {
      environment(name: 'staging', id: 1) {
          description 'Staging env'
          tasks {
          }
          triggers {
          }
          variables {
          }
          notifications {
          }
          permissions {
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  deploymentProjects:
    - id: 1
      name: DP1
      environments:
        - name: staging
          id: 1
          description: Staging env
          tasks:
          variables:
          notifications:
          permissions:
          triggers:
  ~~~
  {: title="YAML" }
---
A deployment project environment consists of [tasks](#tasks), [build variables](#variables),
[notifications](#notifications), [permissions](#permissions) and [triggers](#environment_triggers).

Note that the ID for both the deployment projects and the environments is optional and will be auto-generated if not
specified.