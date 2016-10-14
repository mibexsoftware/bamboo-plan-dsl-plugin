package ch.mibex.bamboo.plandsl.dsl


abstract class AbstractBambooElement implements BambooElement {
    protected final BambooFacade bambooFacade

    // for testing
    AbstractBambooElement() {
        bambooFacade = new NullBambooFacade()
    }

    AbstractBambooElement(BambooFacade bambooFacade) {
        this.bambooFacade = bambooFacade
    }

    void logDeprecationWarning() {
        List<StackTraceElement> currentStackTrack = DslScriptHelper.stackTrace
        String details = DslScriptHelper.collectSourceDetails(currentStackTrack)
        logDetailedDeprecationWarning(currentStackTrack[0].methodName, details)
    }

    private void logDetailedDeprecationWarning(String subject, String details) {
        def message = "${subject} is deprecated"
        bambooFacade.log("Warning: $details $message")
    }
}
