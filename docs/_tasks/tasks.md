---
title: Tasks
position: 1.0
right_code: |
  ~~~ groovy
  job(key: 'PACKAGE', name: 'Packages the software') {
      tasks {
          // your tasks
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  jobs:
   - key: PACKAGE
     name: Packages the software
     tasks:
       # your tasks
  ~~~
  {: title="YAML" } 
---
Defines the task(s) for this build [job](#jobs) or [deployment project environment](#environments).

The DSL supports the following built-in tasks:

  - [Artifact Download](#artifact_download)
  - [Checkout](#checkout)
  - [Clean working dir](#clean_working_dir)
  - [Command](#command)
  - [Deploy Plug-in](#deploy_plugin)
  - [Docker](#docker)
  - [Heroku Deploy WAR](#heroku_deploy_war)
  - [Inject Bamboo Variables](#inject_bamboo_variables)
  - [JUnit parser](#junit_parser)
  - [Maven 3.x](#maven3)
  - [MSBuild](#msbuild)
  - [NodeJS](#nodejs)
  - [NPM](#npm)
  - [SCP](#scp)
  - [Script](#script)
  - [ShipIt to Marketplace](#shipit2marketplace)
  - [SSH](#ssh)

For other tasks not listed here, please see [custom tasks](#custom_task) on how to use them.