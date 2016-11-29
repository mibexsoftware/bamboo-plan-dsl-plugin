package ch.mibex.bamboo.plandsl.dsl

class Validations {

    private Validations() {}

    static void isTrue(boolean expr, String errorMsg) {
        if (!expr) {
            throw new DslScriptException(errorMsg)
        }
    }

    static <T> T isNotNullOrEmpty(T obj, String errorMsg) {
        if (!obj) {
            throw new DslScriptException(errorMsg)
        }
        obj
    }

    static void isValidBambooEntityName(String entityName, String errorMsg) {
        isTrue(entityName ==~ /[A-Za-z0-9_\-. ]+/, errorMsg)
    }

    static String isSafeBambooString(String str) {
        isTrue(str ==~ /[^"&<>]*/, 'The entry does not contain safe characters')
        str
    }

}
