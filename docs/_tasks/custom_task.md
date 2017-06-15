---
title: Custom tasks
position: 2.8
right_code: |
  ~~~groovy
  tasks {
      custom(pluginKey: 'ch.mibex.bamboo.sonar4bamboo:sonar4bamboo.maven3task') {
          description 'Analyze with SonarQube Maven'
          configure(
                chosenSonarConfigId: 1,
                useGradleWrapper: false,
                failBuildForSonarErrors: true,
                failBuildForBrokenQualityGates: false,
                executable: 'mvn3',
                illegalBranchCharsReplacement: '_',
                useNewGradleSonarQubePlugin: false,
                sonarJavaTarget: '1.8',
                sonarJavaSource: '1.8',
                environmentVariables: 'JAVA_OPTS="-Xms128m -Xmx1024m"',
                replaceSpecialBranchChars: false,
                buildJdk: 'JDK 1.5',
                additionalProperties: '-sonar.branch="master"',
                autoBranch: true,
                useGlobalSonarServerConfig: true,
                workingSubDirectory: 'src'
          )
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
There exist dozens of community-provided Bamboo tasks in the Atlassian Marketplace where the DSL does not provide 
special syntax for. This does not mean that these tasks cannot be used in the plug-in. The DSL offers a standard way
to use and configure any task with the syntax custom. See the example here for the 
<a href="https://marketplace.atlassian.com/plugins/ch.mibex.bamboo.sonar4bamboo/server/overview">Sonar for Bamboo</a>
Maven Task.

As you can see, there are default attributes (description and enabled) any task has and there is a configure block
with the task-specific fields. You probably ask yourself how you can get the plug-in task key 
(`ch.mibex.bamboo.sonar4bamboo:sonar4bamboo.maven3task`) and the field names (like `sonarJavaSource`). The way to 
retrieve these values is to add the Bamboo task you want to use to your build job, configure it as you would like
to have it, and then save the configuration form while watching the POST request in your browser's dev tools. 
Please consult <a href="https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki/Bamboo-tasks">the Wiki</a> 
for more information.