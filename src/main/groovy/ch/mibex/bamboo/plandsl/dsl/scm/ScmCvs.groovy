package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.auth.AdvancedCvsOptions
import ch.mibex.bamboo.plandsl.dsl.scm.auth.AuthType
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class ScmCvs extends ScmType {
    private String cvsRoot
    private AuthType authType
    private int quietPeriodInSeconds
    private String module
    private CvsModuleVersion moduleVersion
    private AdvancedCvsOptions advancedOptions

    // for tests:
    protected ScmCvs() {}

    ScmCvs(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * The full path to your CVS repository root. Bamboo supports pserver, ext (ssh) and local repository
     * access methods.
     */
    void cvsRoot(String cvsRoot) {
        this.cvsRoot = cvsRoot
    }

    /**
     * CVS checkins are not atomic. How many seconds should Bamboo wait between checkins to determine if the checkin
     * is complete?
     */
    void quietPeriodInSeconds(int quietPeriodInSeconds) {
        this.quietPeriodInSeconds = quietPeriodInSeconds
    }

    /**
     * The repository module containing the source code.
     */
    void module(String module) {
        this.module = module
    }

    /**
     * Which version of the module should Bamboo build?
     */
    void moduleVersion(CvsModuleVersion moduleVersion) {
        this.moduleVersion = moduleVersion
    }

    void passwordAuth(@DelegatesTo(PasswordAuth) Closure closure) {
        def authByPassword = new PasswordAuth(bambooFacade)
        DslScriptHelper.execute(closure, authByPassword)
        authType = authByPassword
    }

    void advancedOptions(@DelegatesTo(AdvancedCvsOptions) Closure closure) {
        def advancedOptions = new AdvancedCvsOptions(bambooFacade)
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
