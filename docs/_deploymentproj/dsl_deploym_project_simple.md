---
title: Simple Deployment Project example
description: Example not working !!!!
position: 1.1
right_code: |
  ~~~groovy
  deploymentProject(name: 'name') {
      description 'desc'
      useCustomPlanBranch "PLAN_BRANCH_NAME"

      // environments:
      environment(name: 'env1') {
          description 'desc'
          tasks {               
          }
          triggers {
          }
      }
      environment(name: 'env2') {
          description 'desc'
          tasks {               
          }
          triggers {
          }
      }
  }                 
  ~~~
  {: title="DSL" }

---
A deployment project consists of a description, the name of the associated plan branch (if not the default one), and a number of deployment environments:



