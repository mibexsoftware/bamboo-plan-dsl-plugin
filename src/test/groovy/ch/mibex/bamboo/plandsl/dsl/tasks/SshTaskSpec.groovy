package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SshWithoutPassphraseAuth
import spock.lang.Specification

class SshTaskSpec extends Specification {

    def 'SSH task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/SshTask.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].stages[0].jobs[0].tasksList.tasks[0] == new SshTask(
                enabled: true,
                isFinal: false,
                description: 'login to remote server',
                host: 'localhost',
                userName: 'bob',
                authType: new SshWithoutPassphraseAuth(privateKey: 'MY_PRIVATE_KEY'),
                command: 'ls -l',
                advancedOptions: new SshAdvancedOptions(
                        hostFingerprint: 'test',
                        port: 22
                )
        )
    }
}