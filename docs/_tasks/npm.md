---
title: NPM
position: 2.1
right_code: |
  ~~~groovy
  tasks {
      npm(executable: '/usr/bin/nodejs', command: 'install') {
          description 'execute NPM'
          workingSubDirectory '.'
          useIsolatedCache true
          environmentVariables 'what=EVER'
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
NPM package manager for Node.js.