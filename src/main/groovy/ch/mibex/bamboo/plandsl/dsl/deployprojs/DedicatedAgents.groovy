package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.jobs.Requirements
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.9.0
 */
@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class DedicatedAgents extends BambooObject {
    private Requirements requirements
    private DedicatedAgents dedicatedAgents

    DedicatedAgents(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    protected DedicatedAgents() {}

    /**
     * Defines the requirements(s) for this environment.
     *
     * @since 1.9.0
     */
    Requirements dedicatedAgent(@DelegatesTo(value = Requirements, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def newRequirements = new Requirements(bambooFacade)
        DslScriptHelper.execute(closure, newRequirements)
        requirements = newRequirements
    }
}
