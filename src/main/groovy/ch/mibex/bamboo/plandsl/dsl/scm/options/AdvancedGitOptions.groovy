package ch.mibex.bamboo.plandsl.dsl.scm.options

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true, includeSuper=true)
class AdvancedGitOptions extends AdvancedGitRepoOptions {
    private boolean enableRepositoryCachingOnRemoteAgents

    AdvancedGitOptions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected AdvancedGitOptions() {}

    /**
     * Cache repositories on remote agents to save bandwidth. Note: caches are always full clones of
     * the source repository.
     */
    void enableRepositoryCachingOnRemoteAgents(boolean enableRepositoryCachingOnRemoteAgents = true) {
        this.enableRepositoryCachingOnRemoteAgents = enableRepositoryCachingOnRemoteAgents
    }

}
