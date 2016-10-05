package ch.mibex.bamboo.plandsl.dsl.tasks

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class ArtifactInfo {
    final long artifactId
    final int transferId
    final long taskId
    final String name

    ArtifactInfo(long artifactId, int transferId, long taskId, String name) {
        this.artifactId = artifactId
        this.transferId = transferId
        this.taskId = taskId
        this.name = name
    }

    ArtifactInfo(long artifactId, String name) {
        this.artifactId = artifactId
        this.taskId = -1
        this.name = name
    }
}
