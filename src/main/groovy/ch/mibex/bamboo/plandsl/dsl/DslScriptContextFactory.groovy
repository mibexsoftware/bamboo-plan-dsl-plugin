package ch.mibex.bamboo.plandsl.dsl

import org.apache.tools.ant.types.FileSet
import org.apache.tools.ant.types.PatternSet

class DslScriptContextFactory {

    private DslScriptContextFactory() {}

    static Set<DslScriptContext> createContexts(String dslLocations,
                                                boolean inlineScript,
                                                String dslText,
                                                File rootDir) {
        Set<DslScriptContext> scriptContexts = new LinkedHashSet<>()

        if (inlineScript) {
            scriptContexts << new DslScriptContext(dslText)
        } else {
            dslLocations.split('\n').each { target ->
                def fileSet = new FileSet()
                fileSet.setDir(rootDir)
                PatternSet.NameEntry include = fileSet.createInclude()
                include.setName(target)

                def ds = fileSet.getDirectoryScanner(new org.apache.tools.ant.Project())
                String[] srcFiles = ds.includedFiles

                if (srcFiles.length == 0) {
                    throw new DslException("no DSL scripts found at $target")
                }
                srcFiles.each { srcFile ->
                    def file = new File(rootDir, srcFile)
                    def urlRoot = file.toURI().toURL()
                    scriptContexts << new DslScriptContext(file.absolutePath, null, urlRoot)
                }
            }
        }

        scriptContexts
    }
}
