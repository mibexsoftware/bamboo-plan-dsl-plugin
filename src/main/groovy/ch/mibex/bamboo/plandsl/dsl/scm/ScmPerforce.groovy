package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedPerforceOptions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class ScmPerforce extends ScmType {
    private String port
    private String client
    private String depotView
    private String environmentVariables
    private boolean letBambooManageWorkspace
    private boolean useClientMapping
    private PasswordAuth passwordAuth
    private AdvancedPerforceOptions advancedOptions

    // for tests:
    protected ScmPerforce() {}

    protected ScmPerforce(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * The port the perforce client connects to or the perforce server itself.
     */
    void port(String port) {
        this.port = port
    }

    /**
     * The name of the client workspace.
     */
    void client(String client) {
        this.client = client
    }

    /**
     * The workspace view of the depot containing the source code files. This must be in the format
     * //client_name/workspace_mapping/...
     */
    void depotView(String depotView) {
        this.depotView = depotView
    }

    /**
     * Extra environment variables. e.g. P4CHARSET="utf8". You can add multiple parameters separated by a space.
     */
    void environmentVariables(String envVariables) {
        this.environmentVariables = envVariables
    }

    /**
     * Select this option if you use overlay mappings for your workspace. Your workspace must be available on the
     * Bamboo server.
     */
    void useClientMapping(boolean useClientMapping = true) {
        this.useClientMapping = useClientMapping
    }

    /**
     * Bamboo can manage your workspace and set where source code is checked out to. This is useful if your plan will
     * run on many machines.
     */
    void letBambooManageWorkspace(boolean letBambooManageWorkspace = true) {
        this.letBambooManageWorkspace = letBambooManageWorkspace
    }

    void passwordAuth(@DelegatesTo(value = PasswordAuth, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        passwordAuth = new PasswordAuth(bambooFacade)
        DslScriptHelper.execute(closure, passwordAuth)
    }

    void advancedOptions(
            @DelegatesTo(value = AdvancedPerforceOptions, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        advancedOptions = new AdvancedPerforceOptions(bambooFacade)
        DslScriptHelper.execute(closure, advancedOptions)
    }

}
