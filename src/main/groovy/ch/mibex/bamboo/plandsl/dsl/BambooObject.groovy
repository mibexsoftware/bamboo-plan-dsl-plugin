package ch.mibex.bamboo.plandsl.dsl

abstract class BambooObject {
    protected final BambooFacade bambooFacade

    // for testing
    protected BambooObject() {
        bambooFacade = new NullBambooFacade()
    }

    protected BambooObject(BambooFacade bambooFacade) {
        Validations.requireNotNullOrEmpty(bambooFacade, 'Bamboo facade must not be null')
        this.bambooFacade = bambooFacade
    }

    // is called indirectly by AST transformation
    void logDeprecationWarning() {
        List<StackTraceElement> currentStackTrack = DslScriptHelper.stackTrace
        String details = DslScriptHelper.collectSourceDetails(currentStackTrack)
        def message = "${currentStackTrack[0].methodName} is deprecated"
        bambooFacade.log("warning: ${details} $message")
    }

    BambooEnvironment env() {
        bambooFacade.variableContext
    }

    @SuppressWarnings('UnnecessaryGetter')
    String env(String key) {
        // the strict Bamboo facade will throw an error if variable with this does not exist
        bambooFacade.getVariableContext()(key) // do not change this getter access
    }

}
