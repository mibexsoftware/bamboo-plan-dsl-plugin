---
title: Plans
position: 1.0
right_code: |
  ~~~ groovy
  project(key: 'PROJECTKEY', name: 'my project') {
      plan(key: 'PLAN1KEY', name: 'my plan 1') {
          description 'description for plan'
          enabled true

          stage(name: 'package') {
          }
          stage(name: 'test') {
          }
          scm {
          }
          triggers {
          }
          branches {
          }
          notifications {
          }
          deploymentProject(name: 'staging') {
          }
          deploymentProject(name: 'production') {
          }
      }
      plan(key: 'PLAN2KEY', name: 'my plan 2') {
          // ...
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  ~~~
  {: title="YAML" }

---
A plan contains every thing related to a build plan in Bamboo. It consists of multiple stages, SCM information, triggers,
branches, notifications and deployment projects. Note that you only need to specify the elements you really need. 

Note that the key of the plan must consist of an uppercase letter followed by one or more uppercase alphanumeric 
characters.