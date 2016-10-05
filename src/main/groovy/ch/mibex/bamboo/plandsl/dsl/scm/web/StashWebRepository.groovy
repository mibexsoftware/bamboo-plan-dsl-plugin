package ch.mibex.bamboo.plandsl.dsl.scm.web

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class StashWebRepository extends WebRepositoryType {
    String url
    String projectKey
    String repositoryName

    void url(String url) {
        this.url = url
    }

    void projectKey(String projectKey) {
        this.projectKey = projectKey
    }

    void repositoryName(String repositoryName) {
        this.repositoryName = repositoryName
    }
}
