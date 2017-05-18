---
title: Project definition
position: 1.0
right_code: |
  ~~~ groovy
    project(key: 'PROJECTKEY', name: 'project name') {
        plan(key: 'PLANKEY1', name: 'plan 1') {
            // stages, deployment projects, repositories, branches, ...
        }
        plan(key: 'PLANKEY2', name: 'plan 2') {
            // stages, deployment projects, repositories, branches, ...
        }
        // ...
    }
  ~~~
  {: title="DSL" }

---
A project has a ```key```, a ```name``` and multiple ```plan definitions```. Of course, if you only want to edit one plan then you only have to specify this one plan and not all the other plans of the project.
