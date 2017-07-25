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
class AgentsAssignment extends BambooObject {
    private Requirements requirements = new Requirements(bambooFacade)
    private List<String> dedicatedAgentNames = []

    AgentsAssignment(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    protected AgentsAssignment() {}

    /**
     * Defines the requirements(s) for this environment.
     *
     * @since 1.9.0
     */
    Requirements requirements(@DelegatesTo(value = Requirements, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def newRequirements = new Requirements(bambooFacade)
        DslScriptHelper.execute(closure, newRequirements)
        requirements = newRequirements
    }

    /**
     * Defines dedicated specific agents or images to execute all deployments for this environment.
     *
     * @param agentNames the names of the agents
     *
     * @since 1.9.0
     */
    void dedicatedAgents(String... agentNames) {
        dedicatedAgentNames = agentNames
    }
}
