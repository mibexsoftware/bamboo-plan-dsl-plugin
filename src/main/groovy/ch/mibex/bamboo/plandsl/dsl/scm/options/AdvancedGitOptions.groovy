package ch.mibex.bamboo.plandsl.dsl.scm.options

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class AdvancedGitOptions extends AdvancedGitRepoOptions {
    boolean enableRepositoryCachingOnRemoteAgents

    void enableRepositoryCachingOnRemoteAgents(boolean enableRepositoryCachingOnRemoteAgents) {
        this.enableRepositoryCachingOnRemoteAgents = enableRepositoryCachingOnRemoteAgents
    }

}
