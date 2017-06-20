---
title: Script
position: 1.1
right_code: |
  ~~~groovy
  tasks {
      script() {
          description 'my task'
          inline {
              interpreter: !scriptInterpreter LEGACY_SH_BAT
              scriptBody 'echo "Hello World"'
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  tasks:
    - !script
      description my task
      inlineScript:
        interpreter: !scriptInterpreter LEGACY_SH_BAT
        scriptBody: echo "Hello World"
  ~~~
  {: title="YAML" }
---
A simple script task.