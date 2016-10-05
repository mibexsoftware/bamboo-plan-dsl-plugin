package ch.mibex.bamboo.plandsl.dsl

import org.codehaus.groovy.runtime.StackTraceUtils

class DslScriptHelper {

    private DslScriptHelper() {}

    static void execute(Closure closure, Object delegate) {
        if (closure) {
            closure.delegate = delegate
            closure.resolveStrategy = Closure.DELEGATE_FIRST
            closure.call()
        }
    }

    // inspired by Jenkins Job DSL plug-in:
    static String collectSourceDetails(StackTraceElement[] stackTrace) {
        StackTraceElement element = stackTrace.find {
            StackTraceUtils.isApplicationClass(it.className) && !it.className.startsWith('ch.mibex.bamboo.plandsl.dsl')
        }
        sourceDetailsForScript(element?.fileName, element ? element.lineNumber : -1)
    }

    private static String sourceDetailsForScript(String scriptName, int line) {
        def details = '???'
        if (scriptName) {
            details = scriptName.matches(/script\d+\.groovy/) ? 'DSL script' : scriptName
            if (line > 0) {
                details += ", line $line"
            }
        }
        "[$details]:"
    }

}
