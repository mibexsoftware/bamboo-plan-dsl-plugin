---
title: ShipIt to Marketplace
position: 1.8
right_code: |
  ~~~groovy
  tasks {
      shipIt2marketplace(deployArtifactName: 'Plan DSL') {
          description 'ship it to the Atlassian Marketplace'
          enabled true
          isFinal false
          onlyAllowToTriggerFromJira true
          runOnBranchBuilds false
          publicVersion true
          deduceBuildNrFromPluginVersion true
          bambooUserId 'admin'
          jqlToCollectReleaseNotes 'status in (resolved,closed,done)'
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
Deploys your plug-ins to the Atlassian Marketplace. This task is provided by the
<a href="https://marketplace.atlassian.com/plugins/ch.mibex.bamboo.shipit2mpac/server/overview">ShipIt to Marketplace for Bamboo</a>
plug-in.