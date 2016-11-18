package ch.mibex.bamboo.plandsl.dsl

import org.codehaus.groovy.runtime.StackTraceUtils

class DslScriptHelper {
    private static final Set<String> CLASS_FILTER = [
            DslScriptHelper.name,
            BambooFacade.name,
            BambooObject.name
    ]

    private DslScriptHelper() {}

    static void execute(Closure closure, Object delegate) {
        if (closure) {
            closure.delegate = delegate
            closure.resolveStrategy = Closure.DELEGATE_FIRST
            closure.call()
        }
    }

    static List<StackTraceElement> getStackTrace() {
        Thread.currentThread().stackTrace.findAll { StackTraceElement element ->
            StackTraceUtils.isApplicationClass(element.className) &&
                    !(element.className in CLASS_FILTER) &&
                    !CLASS_FILTER.any { element.className.startsWith(it + '$') }
        }
    }

    // inspired by Jenkins Job DSL plug-in:
    static String collectSourceDetails(List<StackTraceElement> stackTrace) {
        StackTraceElement element = stackTrace.find {
            (StackTraceUtils.isApplicationClass(it.className)
            && !it.className.startsWith('ch.mibex.bamboo.plandsl')
            && !it.className.startsWith('org.springframework')
            && !it.className.startsWith('com.atlassian'))
        }
        sourceDetailsForScript(element?.fileName, element ? element.lineNumber : -1)
    }

    private static String sourceDetailsForScript(String scriptName, int line) {
        def details = '???'
        if (scriptName) {
            // here's an example of a stack trace with a script in it:
            // at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:154)
            // at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:174)
            // at script14763496599551936038337$_run_closure1.doCall(script14763496599551936038337.groovy:4)
            // at script14763496599551936038337$_run_closure1.doCall(script14763496599551936038337.groovy)
            // at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
            // at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
            //
            // the format (filename:linenr) allows to click on a stacktrace element in IDE's like IntelliJ
            details = scriptName.matches(/script\d+\.groovy/) ? '(script' : "($scriptName"
            if (line > 0) {
                details += ":$line)"
            }
        }
        "$details:"
    }

}
