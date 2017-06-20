---
title: Environments
position: 1.0
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
A deployment project environment consists of tasks, build variables, notifications, environments and triggers.
