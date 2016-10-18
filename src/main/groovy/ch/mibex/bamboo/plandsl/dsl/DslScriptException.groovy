package ch.mibex.bamboo.plandsl.dsl

class DslScriptException extends DslException {

    DslScriptException(String msg) {
        super(msg)
    }

    DslScriptException(String msg, Exception e) {
        super(msg, e)
    }

    @Override
    String getMessage() {
        def stackTrace = (cause ? cause.stackTrace : stackTrace) as List<StackTraceElement>
        def dslLineInfo = DslScriptHelper.collectSourceDetails(stackTrace)
        "$dslLineInfo ${super.message}"
    }

}
