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

import static ch.mibex.bamboo.plandsl.dsl.Validations.requireTrue

@EqualsAndHashCode(includeFields=true, excludes=['metaClass'], callSuper=true)
@ToString(includeFields=true)
class ScpTask extends Task {
    private static final TASK_ID = 'com.atlassian.bamboo.plugins.bamboo-scp-plugin:scptask'
    private String host
    private String userName
    private AuthType authType
    private ScpArtifactByLocalPath artifactLocalPath
    private String artifactName
    private String remotePath
    private SshAdvancedOptions advancedOptions
    private String localPath

    //for tests
    protected ScpTask() {
        super(TASK_ID)
    }

    /**
     * @param host Hostname or IP address of the remote host
     * @param userName Username you want to use to access the remote host
     * @param bambooFacade
     */
    ScpTask(String host, String userName, BambooFacade bambooFacade) {
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
     * Copy an artifact produced by a Bamboo build job.
     *
     * @param artifactName The name of the artifact.
     */
    void artifactByName(String artifactName) {
        requireTrue(artifactLocalPath == null, 'either artifactByLocalPath OR artifactName must be provided.')
        this.artifactName = artifactName
    }

    /**
     * Location of files to copy to remote host.
     *
     * @param params the parameters of the SCP task. Only "localPath" is supported. "localPath"
     * is a comma separated list of files or directories.
     */
    void artifactByLocalPath(
            Map<String, String> params,
            @DelegatesTo(value = ScpArtifactByLocalPath, strategy = Closure.DELEGATE_FIRST) Closure c) {
        requireTrue(artifactName == null, 'either artifactByLocalPath OR artifactName must be provided.')
        this.localPath = params['localPath']
        def artifactByLocalPath = new ScpArtifactByLocalPath()
        DslScriptHelper.execute(c, artifactByLocalPath)
        this.artifactLocalPath = artifactByLocalPath
    }

    void remotePath(String remotePath) {
        this.remotePath = remotePath
    }

    void advancedOptions(@DelegatesTo(value = SshAdvancedOptions, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def advancedScpOptions = new SshAdvancedOptions()
        DslScriptHelper.execute(closure, advancedScpOptions)
        this.advancedOptions = advancedScpOptions
    }

    @Override
    protected Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('host', host)
        config.put('username', userName)

        if (artifactLocalPath && localPath) {
            config.put('localPath', localPath)
            config.put('useAntPattern', artifactLocalPath.useAntPatternsToSelectFiles.toString())
        } else if (artifactName) {
            def artifactId = getArtifactId(context, artifactName, true)
            config.put('artifactToScp', artifactId)
        }
        config.put('remotePath', remotePath)
        if (advancedOptions) {
            if (advancedOptions.hostFingerprint) {
                config.put('verifyFingerprint', true.toString())
                config.put('fingerprint', advancedOptions.hostFingerprint)
            }
            config.put('port', advancedOptions.port.toString())
        }
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
                config.put('private_key', bambooFacade.encrypt((authType as SshWithoutPassphraseAuth).privateKey))
                break
        }
        config
    }

}
