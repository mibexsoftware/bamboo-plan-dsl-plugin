package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class CustomTaskSpec extends Specification {

    def 'custom task with map syntax'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/CustomTaskMapSyntax.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))
        CustomTask customTask = results.projects[0].plans[0].stages[0].jobs[0].tasks.tasks[0]

        then:
        customTask.pluginKey == "ch.mibex.bamboo.sonar4bamboo:sonar4bamboo.maven3task"
        customTask.enabled
        customTask.description == "Analyze with SonarQube"
        customTask.taskConfig == [
                              chosenSonarConfigId           : "1",
                              useGradleWrapper              : "false",
                              failBuildForBrokenQualityGates: "false",
                              failBuildForSonarErrors       : "true",
                              executable                    : 'mvn3',
                              illegalBranchCharsReplacement : '_',
                              useNewGradleSonarQubePlugin   : "false",
                              sonarJavaTarget               : '1.8',
                              sonarJavaSource               : '1.8',
                              environmentVariables          : 'JAVA_OPTS="-Xms128m -Xmx1024m"',
                              replaceSpecialBranchChars     : 'false',
                              buildJdk                      : 'JDK 1.5',
                              additionalProperties          : '-Dsome.property=some.value',
                              autoBranch                    : 'true',
                              useGlobalSonarServerConfig    : 'true',
                              workingSubDirectory           : 'dir']
    }

    def 'custom task with method syntax'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/CustomTaskMethodSyntax.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))
        CustomTask customTask = results.projects[0].plans[0].stages[0].jobs[0].tasks.tasks[0]

        then:
        customTask.pluginKey == "ch.mibex.bamboo.sonar4bamboo:sonar4bamboo.maven3task"
        customTask.enabled
        customTask.description == "Analyze with SonarQube"
        customTask.taskConfig == [
                              chosenSonarConfigId           : "1",
                              useGradleWrapper              : "false",
                              failBuildForBrokenQualityGates: "false",
                              failBuildForSonarErrors       : "false",
                              executable                    : 'mvn3',
                              illegalBranchCharsReplacement : '_',
                              useNewGradleSonarQubePlugin   : "false",
                              sonarJavaTarget               : '1.8',
                              sonarJavaSource               : '1.8',
                              environmentVariables          : 'JAVA_OPTS="-Xms128m -Xmx1024m"',
                              replaceSpecialBranchChars     : 'false',
                              buildJdk                      : 'JDK 1.5',
                              additionalProperties          : '-Dsome.property=some.value',
                              autoBranch                    : 'true',
                              useGlobalSonarServerConfig    : 'true',
                              workingSubDirectory           : 'dir']
    }

}