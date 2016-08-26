# Plan DSL for Atlassian Bamboo

_A Bamboo plug-in to create and maintain your build plans with a Groovy-based DSL._

This tutorial will help you to get started with the plug-in.

## Adding a seed task to your Bamboo plan
The first step is to add the provided Bamboo task "Seed task" to a build plan of your choice. In this build plan you have your DSL scripts with which you want to populate your build plans with the settings provided from your DSL scripts.

![Adding a seed task to a build plan](highlight1_task.png)

You can choose between adding a DSL script inline or by providing an Ant pattern for the location of your DSL files in the task configuration (the latter files are taken from the checkout out sources of the assoicated repository from this build plan). In the screenshot you see the Ant pattern "scripts/**/*.groovy" which means that all files with a .groovy suffix in the directory `script` and below are taken and executed when the build plan with this seed task is run. For testing, you can also define the DSL script inline. Here's an example of a DSL script to create a basic build plan:

```groovy
project("MYPROJECT") {
    name "My project"

    plan("MYPLAN") {
        stage("My stage") {
            description "My stage"
            manual false

            job("MYJOB") {
                name "My job"
                description "This ismy job"

                tasks {
                    shell("say hello world") {
                        enabled true
                        inline {
                            scriptBody 'echo "Hello world"'
                            runAsPowershellScript false
                        }
                    }
                }
            }
        }
    }
}
```

## Run the build plan with the seed task
After you configured the seed task, you can run the build plan with your seed task and it will create or update (if the plans in your DSL script already exist) all plans in your DSL script(s). Normally, you will use DSL scripts from your SCM and when you push your changes, the build plan with the DSL scripts will run and will adapt your Bamboo build plans to your likings. See the following screenshot from a build plan containing the seed task which created the referenced plan from the example script above:
