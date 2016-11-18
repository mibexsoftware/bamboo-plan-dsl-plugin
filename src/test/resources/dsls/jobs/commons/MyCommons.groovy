package dsls.jobs.commons

import ch.mibex.bamboo.plandsl.dsl.BambooEnvironment
import ch.mibex.bamboo.plandsl.dsl.jobs.Job

class MyCommons {

    static void addJobProperties(Job job, BambooEnvironment bambooEnv, String repoName, String artifactName, String jarFilePattern) {
        job.with {
            name "Build plug-in"
            description "analyzes the code, runs tests and builds the plug-in"
            enabled true

            tasks {
                checkout("Checkout default repository") {
                    forceCleanBuild false

                    repository(repoName) {}
                }
                script("Replace SNAPSHOT in version with timestamp") {
                    enabled true
                    inline {
                        scriptBody '''\
                                          export DATE=`date +"%Y%m%d-%H%M%S"`
                                          sed -i -e "s#SNAPSHOT#$DATE#g" pom.xml'''.stripIndent()
                        interpreter ScriptInterpreter.RUN_AS_EXECUTABLE
                    }
                }
                maven3("Build, test and package") {
                    goal "clean package"
                    executable bambooEnv('maven')
                    buildJdk "JDK Oracle 1.8"
                }
            }

            artifacts {
                definition(artifactName) {
                    copyPattern jarFilePattern
                    shared true
                }
            }
        }
    }

}