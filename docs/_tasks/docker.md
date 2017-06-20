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
          useAnExistingDockerfile()
          workingSubDirectory '.'
          environmentVariables 'what=EVER'
          saveTheImageAsFile 'image.iso'
          doNotUseCachingWhenBuildingImage true
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  tasks:
    - !docker
      repository: registry.address:port/namespace/repository:tag
      description: build docker imag
      command: !dockerCommand BUILD
      existingDockerfile: true
      dockerfileContents: FROM debian:jessie-slim
      workingSubDirectory: .
      environmentVariables: what=EVER
      saveTheImageAsFile: image.iso
      doNotUseCachingWhenBuildingImage: false
  ~~~
  {: title="YAML" }
---
A task to build, run and deploy Docker containers using the Docker command line interface.