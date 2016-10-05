package ch.mibex.bamboo.plandsl.dsl.tasks

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import ch.mibex.bamboo.plandsl.dsl.tasks.ScriptTask.ScriptInterpreter

@EqualsAndHashCode
@ToString
class InlineScript {
    ScriptInterpreter interpreter
    String scriptBody

    void interpreter(ScriptInterpreter interpreter) {
        this.interpreter = interpreter
    }

    void scriptBody(String scriptBody) {
        this.scriptBody = scriptBody
    }
}
