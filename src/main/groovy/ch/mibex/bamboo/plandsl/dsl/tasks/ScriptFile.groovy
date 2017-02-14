package ch.mibex.bamboo.plandsl.dsl.tasks

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import ch.mibex.bamboo.plandsl.dsl.tasks.ScriptTask.ScriptInterpreter

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class ScriptFile {
    private ScriptInterpreter interpreter = ScriptInterpreter.RUN_AS_EXECUTABLE
    private String scriptFile

    /**
     * An interpreter is chosen based on the shebang line of your script.
     */
    void interpreter(ScriptInterpreter interpreter) {
        this.interpreter = interpreter
    }

    /**
     * Script file path.
     */
    void scriptFile(String scriptFile) {
        this.scriptFile = scriptFile
    }
}
