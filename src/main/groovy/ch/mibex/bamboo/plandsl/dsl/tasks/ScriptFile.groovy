package ch.mibex.bamboo.plandsl.dsl.tasks

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import ch.mibex.bamboo.plandsl.dsl.tasks.ScriptTask.ScriptInterpreter

@EqualsAndHashCode
@ToString
class ScriptFile {
    ScriptInterpreter interpreter
    String scriptFile

    void interpreter(ScriptInterpreter interpreter) {
        this.interpreter = interpreter
    }

    void scriptFile(String scriptFile) {
        this.scriptFile = scriptFile
    }
}
