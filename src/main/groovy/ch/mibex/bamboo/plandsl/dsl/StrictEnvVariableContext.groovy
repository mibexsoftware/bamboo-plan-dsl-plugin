package ch.mibex.bamboo.plandsl.dsl

class StrictEnvVariableContext implements EnvVariableContext {
    private final Map<String, String> variableContext

    StrictEnvVariableContext(Map<String, String> variableContext) {
        this.variableContext = variableContext
    }

    @Override
    String getAt(String key) {
        def value = variableContext[key]
        Validations.isNotNullOrEmpty(value, "No environment variable found for '$key'")
        value
    }
}
