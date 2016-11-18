package ch.mibex.bamboo.plandsl.dsl

abstract class BambooObject {
    protected final BambooFacade bambooFacade

    // for testing
    protected BambooObject() {
        bambooFacade = new NullBambooFacade()
    }

    protected BambooObject(BambooFacade bambooFacade) {
        this.bambooFacade = bambooFacade
    }

    // is called indirectly by AST transformation
    void logDeprecationWarning() {
        List<StackTraceElement> currentStackTrack = DslScriptHelper.stackTrace
        String details = DslScriptHelper.collectSourceDetails(currentStackTrack)
        def message = "${currentStackTrack[0].methodName} is deprecated"
        bambooFacade.log("Warning: ${details} $message")
    }

}
