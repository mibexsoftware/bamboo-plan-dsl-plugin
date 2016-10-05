package ch.mibex.bamboo.plandsl.dsl.triggers

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class RemoteTrigger extends TriggerType {
    List<String> repositories
    List<String> ipAddresses

    void ipAddresses(String... ipAddresses) {
        this.ipAddresses = ipAddresses
    }

    void repositories(String... repositories) {
        this.repositories = repositories
    }

}
