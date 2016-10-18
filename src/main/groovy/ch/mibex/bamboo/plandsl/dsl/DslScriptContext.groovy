package ch.mibex.bamboo.plandsl.dsl

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class DslScriptContext {
    final String location
    final String body
    final URL urlRoot
    final String scriptPath

    DslScriptContext(String body) {
        this(null, body, new File('.').toURI().toURL())
    }

    DslScriptContext(File file) {
        this(file.absolutePath, null, file.absoluteFile.toURI().toURL())
    }

    DslScriptContext(String location, String body, URL urlRoot, String scriptPath = null) {
        if (location && !isValidScriptName(location)) {
            throw new DslException('Dashes in script filenames are not allowed: ' +
                    "$location. See https://issues.apache.org/jira/browse/GROOVY-505 for more information.")
        }
        this.location = location
        this.body = body
        this.urlRoot = urlRoot
        this.scriptPath = scriptPath
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
