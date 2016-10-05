package ch.mibex.bamboo.plandsl.dsl.tasks

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class InjectBambooVariablesTask extends Task {
    static final TASK_ID = 'com.atlassian.bamboo.plugins.bamboo-variable-inject-plugin:inject'
    String propertiesFilePath
    String namespace
    VariablesScope variablesScope

    InjectBambooVariablesTask() {
        super(TASK_ID)
    }

    void propertiesFilePath(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath
    }

    void namespace(String namespace) {
        this.namespace = namespace
    }

    void variablesScope(VariablesScope variablesScope) {
        this.variablesScope = variablesScope
    }

    @Override
    def Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('filePath', propertiesFilePath)
        config.put('namespace', namespace)
        config.put('environmentVariables', variablesScope.name())
        config
    }

    static enum VariablesScope {
        LOCAL, RESULT
    }

}
