package dsls.tasks

project(key: 'TEST', name: 'Artifact tests') {
    plan(key: 'ENV', name: 'Environments') {
        deploymentProject('DEPLOY') {
                environment('TEST') {
                }
                environment('STAGING') {
                deploymentTriggers {
                    afterSuccessDeployment(triggeringEnvironment: 'PROD') {
                    }
                }
                tasks {
                    artifactDownload() {
                        artifact(name: 'message') {}
                    }
                    script() {
                        inline {
                            interpreter ScriptInterpreter.LEGACY_SH_BAT
                            scriptBody '''
echo "Deploying to Dev based on my artifact from the package"
echo
cat *.txt
'''
                        }
                    }
                    deployPlugin(
                            productType: ch.mibex.bamboo.plandsl.dsl.tasks.DeployPluginTask.ProductType.STASH,
                            deployUsername: 'admin',
                            deployURL: 'http://myserver',
                            deployArtifactName: 'message',
                            deployPasswordVariable: '${bamboo.bitbucket_server_password}'
                    ) {
                        useAtlassianIdWebSudo false
                        description "Deploy plug-in to staging server"
                        enabled true
                        deployBranchEnabled true
                        certificateCheckDisabled true
                    }
                }
            }
        }

        stage(key: 'DEV', name: 'Run this first') {
            job(key: 'AAA', name: 'Stuff') {
                tasks {
                    script() {
                        inline {
                            interpreter ScriptInterpreter.LEGACY_SH_BAT
                            scriptBody '''
mkdir artifact
echo "Hello Artifact" > artifact/message.txt
'''
                        }
                    }
                }
                artifacts {
                    definition(name: 'message', copyPattern: '*.txt') {
                        location 'artifact/'
                        shared true
                    }
                }
            }
        }
    }
}