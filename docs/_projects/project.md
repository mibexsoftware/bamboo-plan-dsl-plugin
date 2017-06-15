---
title: Project
position: 1.0
right_code: |
  ~~~ groovy
  project(key: 'PROJECTKEY', name: 'my project') {
      plan(key: 'PLAN1KEY', name: 'my plan 1') {
      }
      plan(key: 'PLAN2KEY', name: 'my plan 2') {
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
      - key: PLAN2KEY
        name: my plan 2        
  ~~~
  {: title="YAML" }

---

A project contains of a collection of plans. Each project has a ```key```, a ```name``` and multiple 
plans. Of course, if you only want to mutate one plan then you only have to specify this one plan and not all the other
plans of the project.
