package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class CloverCodeCoverage extends BambooObject {
    private String cloverXmlLocation
    private String cloverLicense
    private CloverOptions cloverOptions
    private IntegrationOptions integrationOptions

    // just for testing
    protected CloverCodeCoverage() {}

    CloverCodeCoverage(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Automatically integrate Clover into this build.
     *
     * @param cloverLicense Specify your Clover license.
     */
    void automaticallyIntegrateCloverIntoBuild(
            String cloverLicense,
            @DelegatesTo(value = CloverOptions, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        this.cloverLicense = cloverLicense
        def cloverOptions = new CloverOptions()
        DslScriptHelper.execute(closure, cloverOptions)
        this.cloverOptions = cloverOptions
    }

    /**
     * Clover is already integrated into this build and a clover.xml file will be produced.
     *
     * @param params Only "cloverLicense" is expected.
     */
    void automaticallyIntegrateCloverIntoBuild(
            Map<String, String> params,
            @DelegatesTo(value = CloverOptions, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        automaticallyIntegrateCloverIntoBuild(params['cloverLicense'], closure)
        integrationOptions = IntegrationOptions.AUTOMATICALLY_INTEGRATE_CLOVER_INTO_BUILD
    }

    /**
     * Clover is already integrated into this build and a clover.xml file will be produced.
     *
     * @param cloverXmlLocation This is where Bamboo will look for the XML report output file from Clover.
     * The Clover report generation must run as part of your build.
     */
    void cloverIsAlreadyIntegratedIntoBuild(String cloverXmlLocation) {
        this.cloverXmlLocation = cloverXmlLocation
        integrationOptions = IntegrationOptions.CLOVER_IS_ALREADY_INTEGRATED_INTO_BUILD
    }

    /**
     * Clover is already integrated into this build and a clover.xml file will be produced.
     *
     * @param params Only "cloverXmlLocation" is expected.
     */
    void cloverIsAlreadyIntegratedIntoBuild(Map<String, String> params) {
        cloverIsAlreadyIntegratedIntoBuild(params['cloverXmlLocation'])
    }

    static enum IntegrationOptions {
        AUTOMATICALLY_INTEGRATE_CLOVER_INTO_BUILD('auto'),
        CLOVER_IS_ALREADY_INTEGRATED_INTO_BUILD('custom')

        String value

        IntegrationOptions(String value) {
            this.value = value
        }
    }

}
