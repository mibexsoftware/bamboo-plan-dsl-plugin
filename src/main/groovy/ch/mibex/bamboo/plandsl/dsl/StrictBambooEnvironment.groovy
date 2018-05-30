package ch.mibex.bamboo.plandsl.dsl

class StrictBambooEnvironment implements BambooEnvironment {
    private final Map<String, String> variableContext

    StrictBambooEnvironment(Map<String, String> variableContext) {
        this.variableContext = variableContext
    }

    @Override
    String getAt(String key) {
        return getFromEnv(key)
    }

    @Override
    String call(String key) {
        return getFromEnv(key)
    }

    private void getFromEnv(String key) {
        def value = variableContext[key]
        Validations.requireNotNullOrEmpty(value, "No environment variable found for '$key' in ${variableContext.keySet().join(",")}")
        value
    }
}
