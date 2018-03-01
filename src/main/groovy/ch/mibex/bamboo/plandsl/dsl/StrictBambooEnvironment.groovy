package ch.mibex.bamboo.plandsl.dsl

class StrictBambooEnvironment implements BambooEnvironment {
    private final Map<String, String> variableContext

    StrictBambooEnvironment(Map<String, String> variableContext) {
        this.variableContext = variableContext
    }

    @Override
    String getAt(String key) {
        def value = variableContext[key]
        Validations.requireNotNullOrEmpty(value, "No environment variable found for '$key'")
        value
    }

    @Override
    String call(String key) {
        def value = variableContext[key]
        Validations.requireNotNullOrEmpty(value, "No environment variable found for '$key'")
        value
    }
}
