package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.auth.AdvancedCvsOptions
import ch.mibex.bamboo.plandsl.dsl.scm.auth.AuthType
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class ScmCvs extends ScmType {
    String cvsRoot
    AuthType authType
    int quietPeriodInSeconds
    String module
    CvsModuleVersion moduleVersion
    AdvancedCvsOptions advancedOptions

    // for tests:
    protected ScmCvs() {}

    ScmCvs(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void cvsRoot(String cvsRoot) {
        this.cvsRoot = cvsRoot
    }

    void quietPeriodInSeconds(int quietPeriodInSeconds) {
        this.quietPeriodInSeconds = quietPeriodInSeconds
    }

    void module(String module) {
        this.module = module
    }

    void moduleVersion(CvsModuleVersion moduleVersion) {
        this.moduleVersion = moduleVersion
    }

    void passwordAuth(@DelegatesTo(PasswordAuth) Closure closure) {
        def authByPassword = new PasswordAuth()
        DslScriptHelper.execute(closure, authByPassword)
        authType = authByPassword
    }

    void advancedOptions(@DelegatesTo(AdvancedCvsOptions) Closure closure) {
        def advancedOptions = new AdvancedCvsOptions()
        DslScriptHelper.execute(closure, advancedOptions)
        this.advancedOptions = advancedOptions
    }

    static enum CvsModuleVersion {
        HEAD('head'),
        BRANCH('branch')

        final String type

        private CvsModuleVersion(String type) {
            this.type = type
        }

        @Override
        String toString() {
            this.type
        }
    }
}
