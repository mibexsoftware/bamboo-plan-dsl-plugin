---
title: Dependencies
position: 1.1
right_code: |
  ~~~ groovy
  plan(key: 'SIMPLEPLAN', name: 'Simple plan') {
      dependencies {
          blockingStrategy DependencyBlockingStrategy.BLOCK_BUILD_IF_PARENT_BUILDS_ARE_QUEUED
          advancedOptions {
              triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully true
              autoDependencyManagement true
              enableDependenciesForAllBranches false
          }
          childPlans 'HELLO-HELLO', 'SEED-SEED'
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  plans:
    - key: SIMPLEPLAN
      name: Simple plan
      dependencies:
        blockingStrategy: !dependencyBlocking BLOCK_BUILD_IF_PARENT_BUILDS_ARE_QUEUED
        advancedOptions:
          enableDependenciesForAllBranches: true
          triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully: true
          autoDependencyManagement: false
        dependencies:
          - planKey: HELLO-HELLO
          - planKey: SEED-SEED
  ~~~
  {: title="YAML" } 
---
Specifies the dependencies for this plan. When the current plan builds successfully, it will trigger the
child plans to build.
