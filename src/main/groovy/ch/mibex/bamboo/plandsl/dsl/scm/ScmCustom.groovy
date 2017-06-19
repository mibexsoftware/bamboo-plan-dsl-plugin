package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class ScmCustom extends ScmType {
    private Map<String, String> config = [:]
    private String pluginKey

    // just for testing
    protected ScmCustom() {}

    ScmCustom(BambooFacade bambooFacade, String pluginKey, String name) {
        super(bambooFacade)
        this.pluginKey = pluginKey
        this.name = name
    }

    def methodMissing(String methodName, args) {
        config << [(methodName): args[0].toString()]
    }

    def configure(Map<String, Object> config) {
        config.each { k, v -> config << [(k): v.toString()] }
    }
}
