package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import ch.mibex.bamboo.plandsl.dsl.scm.auth.AuthType
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SshAuth
import ch.mibex.bamboo.plandsl.dsl.scm.auth.SshWithoutPassphraseAuth
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes=['metaClass'], callSuper=true)
@ToString(includeFields=true)
class SshTask extends Task {
    private static final TASK_ID = 'com.atlassian.bamboo.plugins.bamboo-scp-plugin:sshtask'
    private String host
    private String userName
    private AuthType authType
    private String command
    private SshAdvancedOptions advancedOptions

    //for tests
    protected SshTask() {
        super(TASK_ID)
    }

    /**
     * @param host Hostname or IP address of the remote host
     * @param userName Username you want to use to access the remote host
     * @param bambooFacade
     */
    SshTask(String host, String userName, BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
        this.host = Validations.requireNotNullOrEmpty(host, 'host must not be empty')
        this.userName = Validations.requireNotNullOrEmpty(userName, 'userName must not be empty')
    }

    void passwordAuth(@DelegatesTo(value = PasswordAuth, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        authType = new PasswordAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    void keyWithoutPassphrase(
            @DelegatesTo(value = SshWithoutPassphraseAuth, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        authType = new SshWithoutPassphraseAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    void keyWithPassphrase(@DelegatesTo(value = SshAuth, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        authType = new SshAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    /**
     * Shell command to execute on the remote host
     */
    void command(String command) {
        this.command = command
    }

    void advancedOptions(@DelegatesTo(value = SshAdvancedOptions, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def advancedScpOptions = new SshAdvancedOptions()
        DslScriptHelper.execute(closure, advancedScpOptions)
        this.advancedOptions = advancedScpOptions
    }

    @Override
    protected def Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('host', host)
        config.put('username', userName)
        switch (authType) {
            case PasswordAuth:
                config.put('authType', 'PASSWORD')
                config.put('encPassword', bambooFacade.encrypt((authType as PasswordAuth).password))
                config.put('change_password', true.toString())
                break
            case SshAuth:
                config.put('authType', 'KEY_WITH_PASSPHRASE')
                config.put('private_key', bambooFacade.encrypt((authType as SshAuth).privateKey))
                config.put('passphrase', bambooFacade.encrypt((authType as SshAuth).passPhrase))
                break
            case SshWithoutPassphraseAuth:
                config.put('authType', 'KEY')
                config.put('change_key', true.toString())
                config.put('private_key', bambooFacade.encrypt((authType as SshWithoutPassphraseAuth).privateKey))
                break
        }
        config.put('command', command)
        if (advancedOptions) {
            if (advancedOptions.hostFingerprint) {
                config.put('verifyFingerprint', true.toString())
                config.put('fingerprint', advancedOptions.hostFingerprint)
            }
            config.put('port', advancedOptions.port.toString())
        }
        config
    }

}
