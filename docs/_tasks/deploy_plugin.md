---
title: Deploy plug-in
position: 1.7
right_code: |
  ~~~groovy
  tasks {
      deployPlugin(
          productType: ch.mibex.bamboo.plandsl.dsl.tasks.DeployPluginTask.ProductType.STASH,
          deployUsername: "admin",
          deployURL: "http://myserver",
          deployArtifactName: "Plan DSL",
          deployPasswordVariable: '${bamboo.bitbucket_server_password}'
      ) {
          description "Deploy plug-in to staging server"
          enabled true
          deployBranchEnabled true
          certificateCheckDisabled true
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
Deploys an Atlassian plugin to a remote application server. This task is provided by the
<a href="https://marketplace.atlassian.com/plugins/com.atlassian.bamboo.plugins.deploy.continuous-plugin-deployment/server/overview">Continuous Plugin Deployment for Bamboo</a>
plug-in.