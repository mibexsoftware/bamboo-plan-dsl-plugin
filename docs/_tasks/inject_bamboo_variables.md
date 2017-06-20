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
  tasks:
    - !injectBambooVariables
      propertiesFilePath: path/to/props.txt
      namespace: myNs
      variablesScope: !variablesScope RESULT
  ~~~
  {: title="YAML" }
---
A task to inject Bamboo variables from a file with a simple "key=value" format.