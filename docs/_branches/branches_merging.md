---
title: Merging
position: 2.2
right_code: |
  ~~~groovy
  branches {
      merging {
          gateKeeper {
              checkoutBranch 'SIMPLEPROJECT-SIMPLEPLAN'
              pushEnabled true
          }
          // OR:
          branchUpdater {
              mergeFromBranch 'SIMPLEPROJECT2-SIMPLEPLAN0'
              pushEnabled false
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  branches:
    merging:
      mergeStrategy: !gateKeeper
        planBranchKey: PLANKEY
        pushEnabled: true 
      # OR:
      mergeStrategy: !branchUpdater
        planBranchKey: PLANKEY
        pushEnabled: false    
  ~~~
  {: title="YAML" }
---
Automatic merging can test the merge between branches and push changes back to the repository on a successful
build. This setting will be applied to all new plan branches.