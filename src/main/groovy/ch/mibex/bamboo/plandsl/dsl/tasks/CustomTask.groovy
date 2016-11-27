package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class CustomTask extends Task {
    private Map<String, String> buildConfig = [:]

    CustomTask(BambooFacade bambooFacade, String pluginKey) {
        super(bambooFacade, pluginKey)
    }

    def methodMissing(String methodName, args) {
        buildConfig << [(methodName): args[0].toString()]
    }

    def configure(Map<String, Object> config) {
        config.each { k, v -> buildConfig << [(k): v.toString()] }
    }

    @Override
    Map<String, String> getConfig(Map<Object, Object> context) {
        buildConfig
    }
}
