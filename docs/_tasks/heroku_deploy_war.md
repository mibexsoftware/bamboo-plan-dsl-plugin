---
title: Heroku deploy WAR
position: 2.7
right_code: |
  ~~~groovy
  tasks {
      herokuDeployWar(apiKey: 'key', appName: 'myapp', warFile: 'my.war') {
          enabled true
          description "Deploy WAR to Heroku"
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
Deploy a WAR artifact to Heroku.