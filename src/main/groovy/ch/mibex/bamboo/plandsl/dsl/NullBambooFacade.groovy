package ch.mibex.bamboo.plandsl.dsl


class NullBambooFacade implements BambooFacade {

    @Override
    void requirePlugin(String pluginKey) {
        // nop
    }

    @Override
    void requireMinimumPluginVersion(String pluginKey, String minVersion) {

    }

    @Override
    void requireGlobalVariable(String variableName) {
        // nop
    }

    @Override
    void requireExecutable(String executableName) {
        // nop
    }

    @Override
    void requirePlan(String planKey) {
        // nop
    }

    @Override
    void requireJdk(String jdkName) {
        // nop
    }

    @Override
    void requireLinkedRepository(String repoName) {
        // nop
    }

    @Override
    void requireBambooVersion(String version) {
        // nop
    }

    @Override
    void requireSharedCredentials(String name) {
        // nop
    }

    @Override
    void requireApplicationLink(String name) {
        // nop
    }

    @Override
    Map<String, Object> getExportedBambooObjects() {
        return [:]
    }

    @Override
    void log(String message) {

    }

    @Override
    Logger getBuildLogger() {
        return new NullLogger()
    }

    @Override
    EnvVariableContext getVariableContext() {
        return new NullEnvVariableContext()
    }

    // this is used to allow checking of DSL scripts without throwing errors
    static class NullEnvVariableContext implements EnvVariableContext {
        String getAt(String key) {
            key
        }
    }

}

