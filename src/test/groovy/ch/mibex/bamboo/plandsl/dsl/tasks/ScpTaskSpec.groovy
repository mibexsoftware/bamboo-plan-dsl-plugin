package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import spock.lang.Specification

class ScpTaskSpec extends Specification {

    def 'SCP task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/ScpTask.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        def scpTask = results.projects[0].plans[0].stages[0].jobs[0].tasksList.tasks[0] == new ScpTask(
                enabled: true,
                isFinal: false,
                description: 'Ship it to remote server',
                host: 'localhost',
                userName: 'bob',
                authType: new PasswordAuth(password: 'MY_PASSWORD_VARIABLE'),
                artifactLocalPath: new ScpArtifactByLocalPath(useAntPatternsToSelectFiles: true),
                localPath: '*.zip,*.jar',
                remotePath: 'a/b',
                advancedOptions: new ScpAdvancedOptions(
                        hostFingerprint: 'test',
                        port: 22
                )
        )
    }
}