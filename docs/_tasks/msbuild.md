---
title: MSBuild
position: 2.2
right_code: |
  ~~~groovy
  tasks {
      msbuild(executable: 'msbuild', projectFile: 'MySolution.sln') {
          description 'run MSBuild'
          workingSubDirectory '.'
          environmentVariables 'what=EVER'
          options '/d'
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
Run MSBuild as part of your build.