---
title: Job Definitions
description: Not working !!!!
position: 1.0
right_code: |
  ~~~ groovy
  job(key: 'SIMPLEJOB1', name: 'Job name') {
      description 'This is the job description'
      enabled true

      tasks {
          //...
      }

      artifacts {
          definition(name: 'JAR', copyPattern: '**/*.jar') {
              location 'target'
              shared true
          }
          // more artifact definitions...
      }
  }
  ~~~
  {: title="DSL" }

---
A build job has a key, a name, a description, a flag enabled, and a list of tasks and artifacts:
For the syntax of tasks, see the [Bamboo tasks wiki page](https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki/Bamboo-tasks).



