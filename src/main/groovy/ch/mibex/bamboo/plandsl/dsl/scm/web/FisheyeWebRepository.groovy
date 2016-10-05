package ch.mibex.bamboo.plandsl.dsl.scm.web

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class FisheyeWebRepository extends WebRepositoryType {
    String url
    String repositoryPath
    String repositoryName

    void url(String url) {
        this.url = url
    }

    void repositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath
    }

    void repositoryName(String repositoryName) {
        this.repositoryName = repositoryName
    }
}
