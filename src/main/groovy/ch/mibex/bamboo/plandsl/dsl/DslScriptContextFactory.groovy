package ch.mibex.bamboo.plandsl.dsl

import org.apache.tools.ant.types.FileSet
import org.apache.tools.ant.types.PatternSet

class DslScriptContextFactory {

    private DslScriptContextFactory() {}

    static Set<DslScriptContext> createContexts(List<String> dslLocations,
                                                boolean inlineScript,
                                                String dslText,
                                                ScriptLanguage scriptLanguage,
                                                File rootDir,
                                                List<String> additionalClassPaths) {
        Set<DslScriptContext> scriptContexts = new LinkedHashSet<>()

        if (inlineScript) {
            scriptContexts << new DslScriptContext(dslText, scriptLanguage)
        } else {
            List<URL> classPaths = resolveClassPaths(additionalClassPaths, rootDir)

            dslLocations.each { target ->
                List<String> srcFiles = collectFilesForAntPattern(rootDir, target)

                if (srcFiles.isEmpty()) {
                    throw new DslException("no DSL scripts found at $target")
                }

                srcFiles.each { srcFile ->
                    def file = new File(rootDir, srcFile)
                    if (scriptLanguage == ScriptLanguage.GROOVY_DSL) {
                        def urlRoots = [file.parentFile.toURI().toURL()] + classPaths as URL[]
                        scriptContexts << new DslScriptContext(file.name, null, scriptLanguage, urlRoots)
                    } else {
                        scriptContexts << new DslScriptContext(file.absolutePath, null, scriptLanguage)
                    }
                }
            }
        }

        scriptContexts
    }

    private static List<URL> resolveClassPaths(List<String> additionalClassPaths, File rootDir) {
        additionalClassPaths.collectMany { cp ->
            if (cp.contains('*') || cp.contains('?')) {
                collectFilesForAntPattern(rootDir, cp)
            } else {
                [cp]
            }
        }.collect {
            new File(rootDir, it.toString()).toURI().toURL()
        }
    }

    private static List<String> collectFilesForAntPattern(File rootDir, String target) {
        def fileSet = new FileSet()
        fileSet.setDir(rootDir)
        PatternSet.NameEntry include = fileSet.createInclude()
        include.setName(target)
        def ds = fileSet.getDirectoryScanner(new org.apache.tools.ant.Project())
        ds.includedFiles.toList()
    }
}
