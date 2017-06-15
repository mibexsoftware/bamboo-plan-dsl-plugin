---
title: Inject Bamboo Variables
position: 1.3
right_code: |
  ~~~groovy
  tasks {
      injectBambooVariables(propertiesFilePath: 'envVars.properties', namespace: 'soulmv') {
          description 'Inject Build Variables'
          isFinal true
          variablesScope VariablesScope.RESULT
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
A task.