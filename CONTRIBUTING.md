# Plan DSL for Bamboo - Contributions welcome!
We would especially welcome contributions to support more Bamboo tasks in the DSL. This applies to both built-in 
(see [README.md](README.md) for a list of unsupported built-in tasks) as well as other Bamboo tasks.

## Contribute DSL support for a Bamboo task
The Bamboo plan DSL provides support for a lot of built-in Bamboo tasks like the "Source Code Checkout" task:

```groovy
tasks {
    checkout() {
        description 'checkout repo'
        forceCleanBuild true

        repository(name: 'Bamboo Job DSL') {
            checkoutDirectory 'src'
        }
    }
}
```

But there exist hundreds of community-provided Bamboo tasks in the Atlassian Marketplace where the DSL does not provide
special syntax for. This does not mean that these tasks cannot be used in the plug-in. The DSL offers a standard way to 
use and configure any task with the syntax `custom`. See the following example for the 
[Sonar for Bamboo](https://marketplace.atlassian.com/plugins/ch.mibex.bamboo.sonar4bamboo/server/overview) Maven Task:

```groovy
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
``` 

As you can see, there are default attributes (`description` and `enabled`) any task has and there is a `configure` block
with the task-specific fields. You probably ask yourself how you can get the plug-in task key 
(`ch.mibex.bamboo.sonar4bamboo:sonar4bamboo.maven3task`) and the field names (like `sonarJavaSource`). The way to 
retrieve these values is to add the Bamboo task you want to contribute DSL support for use to your build job, configure
it as you would like to have it, and then save the configuration form while watching the POST request in your
 browser's dev tools. See the following example:

![Task configuration form](https://raw.githubusercontent.com/mibexsoftware/bamboo-plan-dsl-plugin/master/doc/images/task-config-ui.png)

The form data of the POST request contains the field values of the Bamboo task:

![View of Chromes browser tools to watch the POST request](https://raw.githubusercontent.com/mibexsoftware/bamboo-plan-dsl-plugin/master/doc/images/task-config-formdata.png)

The task key is also part of the POST request and is named `createTaskKey`. 

While this approach works, it has a number of disadvantages: 

* The names of the properties cannot be checked at compile-time, hence typos cannot be recognized early
* There is no support for the validation of the field names (e.g. to make sure that only valid Java versions are used)
* There is also no possibility to provide documentation for the tasks properties
* The syntax is not as convenient as it could be when providing native support for the Bamboo tasks
 
Hence, if you would like to contribute native support for Bamboo tasks, you would first collect the possible properties
as explained before. Then, you can create a new task class by extending from 
[ch.mibex.bamboo.plandsl.dsl.tasks.Task](src/main/groovy/ch/mibex/bamboo/plandsl/dsl/tasks/Task.groovy).
See the already provided tasks in `ch.mibex.bamboo.plandsl.dsl.tasks` for how to implement support for a task (basically
you create a method for each property you collected above).
 
When you have finished your new task class, please don't forget to register it in 
[ch.mibex.bamboo.plandsl.dsl.tasks.Tasks](src/main/groovy/ch/mibex/bamboo/plandsl/dsl/tasks/Tasks.groovy).
Finally, you want to write a unit test for the new task (see 
[ch.mibex.bamboo.plandsl.dsl.tasks.Maven3Task](src/test/groovy/ch/mibex/bamboo/plandsl/dsl/tasks/Maven3TaskSpec.groovy)
as an example). If you provided support for a built-in Bamboo task, please also check it as resolved in 
[README.md](README.md).

That's all there is to do to provide DSL support for a new task. Happy hacking and we are looking forward to your 
pull request :-)
