---
title: Docker
position: 2.6
right_code: |
  ~~~groovy
  tasks {
      docker(repository: 'registry.address:port/namespace/repository:tag') {
          description 'build docker image'
          command DockerCommand.BUILD
          dockerfileContents 'FROM debian:jessie-slim'
          workingSubDirectory '.'
          environmentVariables 'what=EVER'
          saveTheImageAsFile 'image.iso'
          doNotUseCachingWhenBuildingImage true
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
Build, run and deploy Docker containers using the Docker command line interface.