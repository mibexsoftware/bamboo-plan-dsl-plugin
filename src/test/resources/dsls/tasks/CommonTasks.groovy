package dsls.tasks

import ch.mibex.bamboo.plandsl.dsl.tasks.Tasks

class GroovyCommons {
    static void updateScripts(Tasks tasks) {
        tasks.with {
            script {
                description 'Update scripts'
                inline {
                    scriptBody 'cd /home/bamboo/scripts && git pull'
                    interpreter ScriptInterpreter.LEGACY_SH_BAT
                }
            }
        }
    }

    static void createDeployScripts(Tasks tasks) {
        tasks.with {
            script {
                description 'Prepare deployment'
                file {
                    scriptFile '/home/bamboo/scripts/build/<script>'
                }
            }
        }
    }
}

project(key: 'MYPROJECT', name: 'My project') {
    plan(key: 'MYPLAN', name: 'My plan') {

        stage(name: 'My stage') {
            description 'My stage'
            job(key: 'BUILD', name: 'Maven build job' ) {
                tasks {
                    maven3x(goal: 'install') {
                        description 'build plug-in'
                        executable 'maven323'
                        buildJdk 'jdk8'
                        environmentVariables 'MAVEN_OPTS="-Xmx1024m -XX:MaxPermSize=128m"'
                    }
                }
            }
        }

        deploymentProject(name: 'my deployment project') {
            environment(name: 'staging') {
            }
            environment(name: 'production') {
            }
            environment(name: 'Test') {
                description 'Test env'
                def myTasks = tasks {
                    cleanWorkingDirectory() {}
                }
                GroovyCommons.updateScripts(myTasks)
                GroovyCommons.createDeployScripts(myTasks)
            }
        }
    }
}