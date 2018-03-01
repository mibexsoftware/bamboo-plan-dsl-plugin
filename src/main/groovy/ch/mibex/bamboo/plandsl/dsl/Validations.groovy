package ch.mibex.bamboo.plandsl.dsl

class Validations {
    private static final String ILLEGAL_NAME_CHARACTERS = '[]{}<>:@/&%\\!|#$*;~'

    private Validations() {}

    static boolean requireTrue(boolean expr, String errorMsg) {
        if (!expr) {
            throw new DslScriptException(errorMsg)
        }
        expr
    }

    static <T> T requireNotNullOrEmpty(T obj, String errorMsg) {
        if (!obj) {
            throw new DslScriptException(errorMsg)
        }
        obj
    }

    static String requireValidBambooEntityName(String entityName, String errorMsg) {
        requireNotNullOrEmpty(entityName, errorMsg)
        def invalid = entityName.findAll { a ->
            ILLEGAL_NAME_CHARACTERS.any { a.contains(it) }
        }
        if (invalid) {
            throw new DslScriptException(errorMsg)
        }
        entityName
    }

    static String requireSafeBambooString(String str) {
        requireTrue(str ==~ /[^"'&<>]*/, 'The entry does not contain safe characters')
        str
    }

}
