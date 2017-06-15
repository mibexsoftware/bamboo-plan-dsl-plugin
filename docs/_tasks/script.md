---
title: Script
position: 1.1
right_code: |
  ~~~groovy
  tasks {
      script() {
          description 'my task'
          inline {
              interpreter ScriptInterpreter.LEGACY_SH_BAT
              scriptBody 'echo "Hello World"'
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
A script task.