package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class RemoteTrigger extends TriggerType {
    private List<String> repositories
    private List<String> ipAddresses

    // for tests
    protected RemoteTrigger() {}

    protected RemoteTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Bamboo ensures that triggers originate from IP addresses of the repository server(s). You can
     * authorise additional IP addresses or CIDRs here, separated by a comma.
     */
    void ipAddresses(String... ipAddresses) {
        this.ipAddresses = ipAddresses
    }

    /**
     * Which repositories should the trigger apply to?
     */
    void repositories(String... repositories) {
        this.repositories = repositories
    }

}
