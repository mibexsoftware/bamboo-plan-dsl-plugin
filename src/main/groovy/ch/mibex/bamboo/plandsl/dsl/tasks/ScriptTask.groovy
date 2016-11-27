package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.NullBambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class ScriptTask extends Task {
    private static final TASK_ID = 'com.atlassian.bamboo.plugins.scripttask:task.builder.script'
    private String argument
    private String environmentVariables
    private String workingSubDirectory
    private ScriptFile scriptFile
    private InlineScript inlineScript

    //for tests
    protected ScriptTask() {
        this(new NullBambooFacade())
    }

    ScriptTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    /**
     * File-based script execution.
     */
    void file(@DelegatesTo(ScriptFile) Closure closure) {
        scriptFile = new ScriptFile()
        DslScriptHelper.execute(closure, scriptFile)
    }

    /**
     * Script is given in the Bamboo task.
     */
    void inline(@DelegatesTo(InlineScript) Closure closure) {
        inlineScript = new InlineScript()
        DslScriptHelper.execute(closure, inlineScript)
    }

    /**
     * Script argument.
     */
    void argument(String argument) {
        this.argument = argument
    }

    /**
     * Environment variables.
     */
    void environmentVariables(String environmentVariables) {
        this.environmentVariables = environmentVariables
    }

    /**
     * Working sub directory.
     */
    void workingSubDirectory(String workingSubDirectory) {
        this.workingSubDirectory = workingSubDirectory
    }

    @Override
    protected def Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('argument', argument)
        config.put('scriptBody', inlineScript?.scriptBody ?: '')
        addInterpreterSettings(config)
        config.put('workingSubDirectory', workingSubDirectory)
        config.put('script', scriptFile?.scriptFile ?: '')
        config.put('environmentVariables', environmentVariables)
        config.put('scriptLocation', inlineScript ? 'INLINE' : 'FILE')
        config
    }

    private Object addInterpreterSettings(Map<Object, Object> config) {
        //Bamboo < 5.13
        config.put('runWithPowershell', (inlineScript ?
                inlineScript.interpreter?.toString() :
                scriptFile?.interpreter?.toString())) == ScriptInterpreter.POWERSHELL.name()
        //Bamboo >= 5.13
        config.put('interpreter', inlineScript ?
                inlineScript.interpreter?.toString() :
                scriptFile?.interpreter?.toString())
    }

    static enum ScriptInterpreter {
        RUN_AS_EXECUTABLE, POWERSHELL, LEGACY_SH_BAT
    }

}
