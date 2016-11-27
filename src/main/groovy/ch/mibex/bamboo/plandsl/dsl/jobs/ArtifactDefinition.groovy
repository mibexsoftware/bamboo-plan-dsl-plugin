package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class ArtifactDefinition extends BambooObject {
    private String location
    private String copyPattern
    private boolean isShared
    private String name

    /**
     * Creates an artifact definition.
     *
     * @param name the name of the artifact definition
     */
    ArtifactDefinition(String name, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.name = name
    }

    // just for testing
    protected ArtifactDefinition() {}

    /**
     * Specify the directory (relative path) to find your artifact, e.g. target
     */
    void location(String location) {
        this.location = location
    }

    /**
     * Specify the name (or Ant file copy pattern) of the artifact(s) you want to keep, e.g. &#42;&#42;&#47;&#42;.jar
     */
    void copyPattern(String copyPattern) {
        this.copyPattern = copyPattern
    }

    /**
     * Make the artifact available to be used in other builds and deployments.
     */
    void shared(boolean isShared = true) {
        this.isShared = isShared
    }
}
