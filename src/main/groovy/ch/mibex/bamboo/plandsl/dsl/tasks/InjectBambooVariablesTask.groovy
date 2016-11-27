package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class InjectBambooVariablesTask extends Task {
    private static final TASK_ID = 'com.atlassian.bamboo.plugins.bamboo-variable-inject-plugin:inject'
    private String propertiesFilePath
    private String namespace = 'inject'
    private VariablesScope variablesScope = VariablesScope.LOCAL

    // for tests:
    protected InjectBambooVariablesTask() {
        super(TASK_ID)
    }

    InjectBambooVariablesTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    /**
     * Path to properties file. Each line of the file should be in the form of "key=value".
     */
    void propertiesFilePath(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath
    }

    /**
     * Namespace is used to avoid name conflicts with existing variables.
     */
    void namespace(String namespace) {
        this.namespace = namespace
    }

    /**
     * Scope of the variables.
     */
    void variablesScope(VariablesScope variablesScope) {
        this.variablesScope = variablesScope
    }

    @Override
    protected def Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('filePath', propertiesFilePath)
        config.put('namespace', namespace)
        config.put('scope', variablesScope.toString())
        config
    }

    static enum VariablesScope {
        /**
         * Variables will only be available in this job.
         */
        LOCAL,
        /**
         * Variables will be available in subsequent stages of this plan and in releases created from the result.
         */
        RESULT
    }

}
