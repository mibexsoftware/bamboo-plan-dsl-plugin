package ch.mibex.bamboo.plandsl.dsl.tasks

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import ch.mibex.bamboo.plandsl.dsl.tasks.ScriptTask.ScriptInterpreter

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class InlineScript {
    ScriptInterpreter interpreter
    String scriptBody

    /**
     * An interpreter is chosen based on the shebang line of your script.
     */
    void interpreter(ScriptInterpreter interpreter) {
        this.interpreter = interpreter
    }

    /**
     * Script body.
     */
    void scriptBody(String scriptBody) {
        this.scriptBody = scriptBody
    }
}
