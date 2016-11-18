package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedPerforceOptions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class ScmPerforce extends ScmType {
    String port
    String client
    String depotView
    String environmentVariables
    boolean letBambooManageWorkspace
    boolean useClientMapping
    PasswordAuth passwordAuth
    AdvancedPerforceOptions advancedOptions

    // for tests:
    protected ScmPerforce() {}

    protected ScmPerforce(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void port(String port) {
        this.port = port
    }

    void client(String client) {
        this.client = client
    }

    void depotView(String depotView) {
        this.depotView = depotView
    }

    void environmentVariables(String envVariables) {
        this.environmentVariables = envVariables
    }

    void useClientMapping(boolean useClientMapping = true) {
        this.useClientMapping = useClientMapping
    }

    void letBambooManageWorkspace(boolean letBambooManageWorkspace = true) {
        this.letBambooManageWorkspace = letBambooManageWorkspace
    }

    void passwordAuth(@DelegatesTo(PasswordAuth) Closure closure) {
        passwordAuth = new PasswordAuth()
        DslScriptHelper.execute(closure, passwordAuth)
    }

    void advancedOptions(@DelegatesTo(AdvancedPerforceOptions) Closure closure) {
        advancedOptions = new AdvancedPerforceOptions()
        DslScriptHelper.execute(closure, advancedOptions)
    }

}
