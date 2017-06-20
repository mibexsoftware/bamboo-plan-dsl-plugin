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
  nodes:
    - !npm
      executable: /usr/bin/nodejs
      command: install
      workingSubDirectory: .
      useIsolatedCache: true
      environmentVariables: what=EVER
  ~~~
  {: title="YAML" }
---
A task to use the NPM package manager for Node.js.