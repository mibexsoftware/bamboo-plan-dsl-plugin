# Plan DSL for Bamboo - Treat your build plans as code!

[![Join the chat at https://gitter.im/bamboo-plan-dsl-plugin/Lobby](https://badges.gitter.im/bamboo-plan-dsl-plugin/Lobby.svg)](https://gitter.im/bamboo-plan-dsl-plugin/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

[![Release](https://img.shields.io/github/release/mibexsoftware/bamboo-plan-dsl-plugin.svg?label=maven%20version)](https://jitpack.io/#mibexsoftware/bamboo-plan-dsl-plugin)

[![Travis build status](https://travis-ci.org/mibexsoftware/bamboo-plan-dsl-plugin.svg?branch=master)](https://travis-ci.org/mibexsoftware/bamboo-plan-dsl-plugin)


Note that this repository only contains the source for the plan DSL and YAML support which allows you write your build
plan configurations with autocompletion, syntax highlighting and documentation in your IDE. The 
[Bamboo plug-in](https://marketplace.atlassian.com/plugins/ch.mibex.bamboo.plandsl) itself is available at the 
Atlassian Marketplace. 

For more information, please consult our [wiki](https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki). For
questions regarding the Groovy-based DSL/YAML syntax, please see the
[API documentation](https://mibexsoftware.github.io/bamboo-plan-dsl-plugin/).

## Motivation
We once decided to tag the latest commit of a new release whenever we ship our software to production. Adding the 
necessary build task to achieve this to all our Bamboo build plans was a huge effort because we basically had to click 
through the configuration UI of every build plan. Also, we didn't have the possibility to rollback our plan 
configurations to a specific state and we were not able to see what has changed since the last working configuration.
This was the time where we decided to build this plug-in.

The Atlassian Bamboo plug-in allows you to specify your build plan configurations with a Groovy-based DSL or YAML. It is
conceptually similar and inspired by the well-known Job DSL plug-in for Jenkins. By using Groovy, you have the 
flexibility of a programming language that is well-known for its strong meta-programming and scripting support. By using
the alternative YAML syntax, you have a very lightweight textual description of your build plans and deployment projects.

With our Groovy-based DSL and YAML syntax, you can textually describe your build plans and all its associated Bamboo
concepts like stages, jobs, tasks, build variables, etc. Here's a Groovy-based DSL example of a simple build plan with
an associated deployment project:

```groovy
project(key: 'MYPROJECT', name: 'My project') {
    plan(key: 'MYPLAN', name: 'My plan') {
        stage(name: 'My stage') {
            job(key: 'BUILD', name: 'Maven build job' ) {
                tasks {
                    maven3x(goal: 'install') {
                        description 'build plug-in'
                        executable 'maven323'
                        buildJdk 'jdk8'
                        environmentVariables 'MAVEN_OPTS="-Xmx1024m -XX:MaxPermSize=128m"'
                    }
                }
            }
        }
        
        deploymentProject(name: 'my deployment project') {
            environment(name: 'staging') {
            }
            environment(name: 'production') {
            }
        }
    }
}
```

And here comes the same configuration, but this time using the YAML support:

```yml
project:
  key: MYPROJECT
  name: My project
  plans:
    - key: MYPLAN
      name: My plan
      stages:
        - name: My stage
          jobs:
            - key: BUILD
              name: Maven build job
              tasks:
                - !maven3x
                  goal: install
                  description: build plug-in
                  executable: maven323
                  buildJdk: jdk8
                  environmentVariables: MAVEN_OPTS="-Xmx1024m -XX:MaxPermSize=128m"
      deploymentProjects:
              - name: my deployment project
                environments:
                  - name: staging
                  - name: prod
```

By applying configuration as code with our plug-in, you will always able to go back in the history of your DSL repository
when a build breaks. And you spend less time clicking through the dozens of configuration screens the Bamboo UI 
provides. And last but not least, it is also much more fun to hack your build plans :-)


## Basic usage
See the [wiki](https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki) for more details and examples.

1. Write your plan Groovy DSL or YAML files in your favourite IDE with syntax highlighting, code completion and documentation.
[See the wiki instructions on this](https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki/IDE-support).
2. Add the plug-ins **seed task** to a build plan and reference the DSL/YAML files from the plan's associated repository.
3. When you run the plan with the seed task, the plug-in will automatically create and update all plans specified
   in the DSL/YAML files. You can see which plans got created/updated on the build summary page.
   
   
## Artifacts
You can find the latest JAR file for the DSL in the [projects releases](https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/releases).
Note that you can also access the DSL JAR in your Maven/Gradle build as explained in the 
[IDE support section in the wiki](https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki/IDE-support). 


## Implemented Bamboo Features
This list shows the already supported features by the Plan DSL (note that although a lot of tasks are not yet natively
supported by the plug-in, they can still be used with the 
[custom syntax explained in the wiki](https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki/Bamboo-tasks)).

- Project 
    - [x] Project details
- Plans
    - [x] Plan details
    - [x] Stages
    - [x] Repositories
    - [x] Triggers
    - Branches
        - [x] Branches Options
        - [x] Branch Details
        - [ ] Source repository
        - [ ] Notifications
        - [x] Variables
    - [x] Dependencies
    - [x] Permissions
    - [x] Notifications
    - [x] Variables
    - [x] Miscellaneous
- Stage
    - [x] Stage details
- Job
    - [x] Job details
    - [x] Tasks
    - [x] Requirements
    - [x] Artifacts
    - [x] Miscellaneous
- Tasks (tasks not yet supported can still be used by using "customTask")
    - [ ] Ant
    - [x] Artifact Download
    - [ ] AWA CodeDeploy
    - [ ] Bower
    - [x] Command
    - [x] Deploy Plugin
    - [ ] Deploy Tomcat Application
    - [x] Docker
    - [ ] Dump variables to log
    - [ ] Grails
    - [ ] Grunt 0.4.x
    - [ ] Gulp
    - [x] Heroku: Deploy WAR Artifact
    - [x] Inject Bamboo variables
    - [x] JUnit Parser
    - [ ] Maven 1.x
    - [ ] Maven 2.x
    - [x] Maven 3.x
    - [ ] Maven Dependencies Processor
    - [ ] MBUnit Parser
    - [ ] Mocha Test Parser
    - [ ] Mocha Test Runner
    - [x] MSBuild
    - [ ] MSTest Parser
    - [ ] MSTest Runner
    - [ ] NAnt
    - [x] Node.js
    - [ ] Nodeunit
    - [x] npm
    - [ ] NUnit Parser
    - [ ] NUnit Runner
    - [ ] PHPUnit
    - [ ] PHPUnit 3.3.x
    - [ ] Reload Tomcat Application
    - [x] SCP Task
    - [x] Script
    - [x] Source Code Checkout
    - [x] SSH Task
    - [ ] Start Tomcat Application
    - [ ] Stop Tomcat Application
    - [ ] TestNG Parser
    - [ ] Undeploy Tomcat Application
    - [ ] VCS Branching
    - [ ] VCS Tagging
    - [ ] Visual Studio
- Deployment Projects
    - [x] Details
    - [x] Project permissions
    - [x] Release versioning
    - Environments
        - [x] Details
        - [x] Tasks
        - [x] Triggers
        - [x] Agent assignments
        - [x] Notifications
        - [x] Variables
        - [x] Environment permissions
