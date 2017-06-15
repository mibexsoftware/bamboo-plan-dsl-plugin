package ch.mibex.bamboo.plandsl.dsl.plans

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Miscellaneous extends BambooObject {
    private boolean expireNothing
    private ExpirationDetails expirationDetails
    private Map<String, String> customSettings = [:]

    // just for testing
    protected Miscellaneous() {}

    Miscellaneous(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Nothing will be expired for this plan.
     */
    void expireNothing(boolean enabled = true) {
        expireNothing = enabled
    }

    /**
     * Bamboo will remove expired data when the global build expiry schedule is triggered. You can override the global
     * criteria for expired data, for this plan, with this method.
     */
    void expire(@DelegatesTo(value = ExpirationDetails, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def expirationDetails = new ExpirationDetails(bambooFacade)
        DslScriptHelper.execute(closure, expirationDetails)
        this.expirationDetails = expirationDetails
    }

    /**
     * Configure custom plug-in settings.
     */
    def configure(Map<String, Object> config) {
        config.each { k, v -> customSettings << [(k): v.toString()] }
    }
}
