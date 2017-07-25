---
title: Agent assignments
position: 1.2
right_code: |
  ~~~groovy
  environment(name: 'staging', id: 1) {
      agentsAssignment {
          requirements {
              requirement(capabilityKey: 'system.builder.gradle.Gradle 2.2',
                          matchType: equalsTo("2.2")) {
              }
          }
          dedicatedAgents 'local agent', 'remote agent'
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  environments:
    - name: staging
      agentsAssignment:
        requirements:
          - capabilityKey: system.builder.gradle.Gradle 2.2
            matchType: !equals
              matchValue: 2.2
        dedicatedAgentNames:
          - local agent
          - remote agent      
  ~~~
  {: title="YAML" }
---
An environment can only be deployed by agents whose capabilities meet these requirements.
