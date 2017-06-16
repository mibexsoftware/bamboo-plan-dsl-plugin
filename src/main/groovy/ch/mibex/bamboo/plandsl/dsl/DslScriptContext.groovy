package ch.mibex.bamboo.plandsl.dsl

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class DslScriptContext {
    final String location
    final String body
    final URL[] urlRoots
    final ScriptLanguage scriptLanguage

    // we need this for backwards-compatibility in the unit tests
    DslScriptContext(String body) {
        this(body, ScriptLanguage.GROOVY_DSL)
    }

    DslScriptContext(String body, ScriptLanguage scriptLanguage) {
        this(null, body, scriptLanguage, new File('.').toURI().toURL())
    }

    DslScriptContext(File file, ScriptLanguage scriptLanguage) {
        this(file.absolutePath, null, scriptLanguage, file.toURI().toURL())
    }

    DslScriptContext(String location, String body, ScriptLanguage scriptLanguage, URL urlRoot) {
        this(location, body, scriptLanguage, urlRoot as URL[])
    }

    DslScriptContext(String location, String body, ScriptLanguage scriptLanguage, URL[] urlRoots) {
        if (location && !isValidScriptName(location)) {
            throw new DslException('Invalid script filename detected. Note that filenames need to be valid Java ' +
                    'identifiers, which e.g. means that "-" in filenames are not allowed. ' +
                    "$location. See https://issues.apache.org/jira/browse/GROOVY-505 for more information.")
        }
        this.location = location
        this.body = body
        this.scriptLanguage = scriptLanguage
        this.urlRoots = urlRoots
    }

    private static boolean isValidScriptName(String scriptFile) {
        String normalizedName = getScriptName(scriptFile)
        if (normalizedName.length() == 0 || !Character.isJavaIdentifierStart(normalizedName.charAt(0))) {
            return false
        }
        for (int i = 1; i < normalizedName.length(); i += 1) {
            if (!Character.isJavaIdentifierPart(normalizedName.charAt(i))) {
                return false
            }
        }
        true
    }

    private static String getScriptName(String scriptFile) {
        String fileName = new File(scriptFile).name
        int idx = fileName.lastIndexOf('.')
        idx > -1 ? fileName[0..idx - 1] : fileName
    }

    @Override
    String toString() {
        if (body) {
            "inline script: ${body.take(80).replaceAll('\n', ' ')}..."
        } else {
            "script file from ${location}"
        }
    }
}
