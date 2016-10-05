package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class ScriptTask extends Task {
    public static final TASK_ID = 'com.atlassian.bamboo.plugins.scripttask:task.builder.script'
    String argument
    String environmentVariables
    String workingSubDirectory
    ScriptFile scriptFile
    InlineScript inlineScript

    ScriptTask() {
        super(TASK_ID)
    }

    void file(@DelegatesTo(ScriptFile) Closure closure) {
        scriptFile = new ScriptFile()
        DslScriptHelper.execute(closure, scriptFile)
    }

    void inline(@DelegatesTo(InlineScript) Closure closure) {
        inlineScript = new InlineScript()
        DslScriptHelper.execute(closure, inlineScript)
    }

    void argument(String argument) {
        this.argument = argument
    }

    void environmentVariables(String environmentVariables) {
        this.environmentVariables = environmentVariables
    }

    void workingSubDirectory(String workingSubDirectory) {
        this.workingSubDirectory = workingSubDirectory
    }

    @Override
    def Map<String, String> getConfig(Map<Object, Object> context) {
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
