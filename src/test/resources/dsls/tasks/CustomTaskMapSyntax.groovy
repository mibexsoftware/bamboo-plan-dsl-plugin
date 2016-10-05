package dsls.tasks

project("SIMPLEPROJECT") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        stage("simple stage") {
            description "this is a simple stage"
            manual false

            job("SIMPLEJOB") {
                name "Simple job"
                description "This is a simple job"
                enabled true

                tasks {
                    custom('ch.mibex.bamboo.sonar4bamboo:sonar4bamboo.maven3task') {
                        enabled true
                        description "Analyze with SonarQube"
                        // this is the preferred way to configure custom tasks
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
                                additionalProperties: '-Dsome.property=some.value',
                                autoBranch: true,
                                useGlobalSonarServerConfig: true,
                                workingSubDirectory: 'dir'
                        )
                    }
                }
            }

        }
    }
}