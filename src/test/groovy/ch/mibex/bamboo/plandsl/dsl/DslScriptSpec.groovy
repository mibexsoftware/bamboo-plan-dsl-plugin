package ch.mibex.bamboo.plandsl.dsl

import spock.lang.Specification
import spock.lang.Unroll

// inspired by Jenkins job DSL plug-in
class DslScriptSpec extends Specification {

    @Unroll
    def 'test DSL script #file.name'(File file) {
        given:

        when:
        def loader = new DslScriptParserImpl()
        loader.parse(new DslScriptContext(file.text))

        then:
        noExceptionThrown()

        where:
        file << findDslFiles()
    }

    private static def findDslFiles() {
        List<File> dslFiles = []
        new File('src/test/resources').eachFileRecurse {
            if (it.name.endsWith('.groovy') &&
                !it.path.contains("/commons/") && // these external helper files are not parsable on their own
                !it.name.startsWith('Invalid')) { // these files contain errors to test the parser
                dslFiles << it
            }
        }
        dslFiles
    }

}