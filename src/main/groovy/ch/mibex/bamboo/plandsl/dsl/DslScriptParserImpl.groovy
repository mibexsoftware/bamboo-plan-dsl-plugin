package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.branches.Branch
import ch.mibex.bamboo.plandsl.dsl.branches.Branches
import ch.mibex.bamboo.plandsl.dsl.notifications.Notifications
import ch.mibex.bamboo.plandsl.dsl.scm.ScmCvs
import ch.mibex.bamboo.plandsl.dsl.scm.ScmType
import ch.mibex.bamboo.plandsl.dsl.tasks.DeployPluginTask
import ch.mibex.bamboo.plandsl.dsl.tasks.InjectBambooVariablesTask
import ch.mibex.bamboo.plandsl.dsl.tasks.ScriptTask
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.codehaus.groovy.runtime.InvokerHelper

class DslScriptParserImpl implements DslScriptParser {
    private final Map<String, Object> exportedBambooObjects

    DslScriptParserImpl(Map<String, Object> exportedBambooObjects) {
        this.exportedBambooObjects = exportedBambooObjects
    }

    DslScriptParserImpl() {
        this([:])
    }

    DslScript parse(DslScriptContext scriptContext,
                    Logger logger = new NullLogger(),
                    EnvVariableContext envContext = new NullEnvVariableContext()) {
        CompilerConfiguration config = createCompilerConfig()
        Class clazz = parseScript(config, scriptContext)
        Binding binding = createBinding(logger, envContext)
        evaluateScript(clazz, binding)
    }

    private static DslScript evaluateScript(Class<Script> clazz, Binding binding) {
        try {
            Script script = InvokerHelper.createScript(clazz, binding)
            script.run()
            script as DslScript
        } catch (GroovyRuntimeException e) {
            throw new DslScriptException(e.message, e)
        }
    }

    private static Class parseScript(CompilerConfiguration config, DslScriptContext scriptContext) {
        ClassLoader parentClassLoader = DslScriptParserImpl.classLoader
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(parentClassLoader, config)
        Class<Script> clazz
        if (scriptContext.body) {
            clazz = groovyClassLoader.parseClass(scriptContext.body)
        } else {
            groovyClassLoader.setResourceLoader(new MyResourceLoader(scriptContext.urlRoot))
            clazz = groovyClassLoader.parseClass(new File(scriptContext.location))
        }
        clazz
    }

    private Binding createBinding(Logger logger, EnvVariableContext envContext) {
        Binding binding = new Binding()
        binding.setVariable('out', logger)
        binding.setVariable('bamboo', envContext)

        exportedBambooObjects.each { key, value ->
            binding.setVariable(key, value)
        }

        binding
    }

    private static CompilerConfiguration createCompilerConfig() {
        def config = new CompilerConfiguration(CompilerConfiguration.DEFAULT)
        config.scriptBaseClass = DslScript.name
        def importCustomizer = new ImportCustomizer()
        // we need to embed these enums in files of the DSL and not in separate files because otherwise lookup
        // does not work in IDEs:
        importCustomizer.addStaticImport(Notifications.name, Notifications.NotificationConditions.simpleName)
        importCustomizer.addStaticImport(InjectBambooVariablesTask.name,
                                         InjectBambooVariablesTask.VariablesScope.simpleName)
        importCustomizer.addStaticImport(Branch.name, Branch.NotifyOnNewBranchesType.simpleName)
        importCustomizer.addStaticImport(Branches.name, Branches.NewPlanBranchesTriggerType.simpleName)
        importCustomizer.addStaticImport(DeployPluginTask.name, DeployPluginTask.ProductType.simpleName)
        importCustomizer.addStaticImport(ScmType.name, ScmType.MatchType.simpleName)
        importCustomizer.addStaticImport(ScmCvs.name, ScmCvs.CvsModuleVersion.simpleName)
        importCustomizer.addStaticImport(ScriptTask.name, ScriptTask.ScriptInterpreter.simpleName)

        config.addCompilationCustomizers(importCustomizer)
        // config.addCompilationCustomizers(new ASTTransformationCustomizer(TypeChecked))
        config
    }

    static class MyResourceLoader implements GroovyResourceLoader {
        private final URL base

        MyResourceLoader(URL base) {
            this.base = base
        }

        URL loadGroovySource(String className) {
            def filename = className.replaceAll(/\./, '/') + '.groovy'
            new URL(base, filename)
        }
    }

    // this is used to allow checking of DSL scripts without throwing errors
    static class NullEnvVariableContext implements EnvVariableContext {
        String getAt(String key) {
            key
        }
    }

}
