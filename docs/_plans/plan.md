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
          dependencies {
          }
          variables {
          }
          miscellaneous {
          }
          deploymentProject(name: 'staging') {
          }
          deploymentProject(name: 'production') {
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  project:
    key: PROJECTKEY
    name: my project
    plans:
      - key: PLAN1KEY
        name: my plan 1
        stages:
          - name: package
          - name: test
        scm:          
        triggers:
        branches:
        notifications:
        dependencies:
        variables:
        miscellaneous:
        deploymentProjects:
          - name: staging
          - name: production
  ~~~
  {: title="YAML" }

---
A plan contains everything related to a build plan in Bamboo. It consists of multiple stages, SCM information, triggers,
branches, notifications, build variables, dependencies, misc options and deployment projects. Note that you only need to
specify the elements you really need.

Also note that the key of the plan must consist of an uppercase letter followed by one or more uppercase alphanumeric
characters.