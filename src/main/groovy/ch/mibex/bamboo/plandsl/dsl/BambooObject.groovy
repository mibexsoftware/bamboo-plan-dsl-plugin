package ch.mibex.bamboo.plandsl.dsl

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
abstract class BambooObject {
    protected final BambooFacade bambooFacade

    // for testing
    protected BambooObject() {
        bambooFacade = new NullBambooFacade()
    }

    protected BambooObject(BambooFacade bambooFacade) {
        Validations.isNotNullOrEmpty(bambooFacade, 'Bamboo facade must not be null')
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
