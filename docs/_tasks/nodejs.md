---
title: NodeJS
position: 2.0
right_code: |
  ~~~groovy
  tasks {
      nodeJs(executable: '/usr/bin/nodejs', script: 'package.json') {
          description 'execute Node'
          workingSubDirectory '.'
          arguments '-n'
          environmentVariables 'what=EVER'
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  tasks:
    - !nodeJs
      executable: /usr/bin/nodejs
      script: package.json
      description: execute Node
      workingSubDirectory: .
      arguments: -n
      environmentVariables: what=EVER
  ~~~
  {: title="YAML" }
---
A task to execute JavaScript on the server with Node.js.
