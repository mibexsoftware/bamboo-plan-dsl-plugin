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
        loader.parse(new DslScriptContext(file.text), null)

        then:
        noExceptionThrown()

        where:
        file << findDslFiles()
    }

    private static List<File> findDslFiles() {
        List<File> files = []
        new File('src/test/resources').eachFileRecurse {
            if (it.name.endsWith('.groovy')) {
                files << it
            }
        }
        files
    }

}