package dsls.tasks

project(key: 'TEST', name: 'Artifact tests') {

    plan(key: 'ARTIFACT', name: 'Build Artifact') {
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

        dependencies {
            dependency(planKey: 'TEST-DOWNLOAD')
        }
    }

    plan(key: 'DOWNLOAD', name: 'Download artifact') {
        stage(key: 'FOLLOW', name: 'And then this') {
            job(key: 'BBB', name: 'Other Stuff') {
                tasks {
                    artifactDownload() {
                        artifact('message') {
                            sourcePlanKey 'TEST-ARTIFACT'
                        }
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
                }
            }
        }
    }
}