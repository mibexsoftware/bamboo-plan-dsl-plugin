package ch.mibex.bamboo.plandsl.dsl

import spock.lang.Specification

class ValidationsSpec extends Specification {

    def 'when null should yield error text'() {
        when:
        Validations.isNotNullOrEmpty(null, 'must not be null')

        then:
        Exception e = thrown(DslScriptException)
        e.message.contains('must not be null')
    }

    def 'when not null should not do anything'() {
        when:
        Validations.isNotNullOrEmpty('project("KEY") {}', 'must not be null')

        then:
        true
    }

    def 'when false should yield error text'() {
        when:
        Validations.isTrue(false, 'must not be false')

        then:
        Exception e = thrown(DslScriptException)
        e.message.contains('must not be false')
    }

    def 'when true should not do anything'() {
        when:
        Validations.isTrue(true, 'must not be false')

        then:
        true
    }

    def 'safe Bamboo string check with non-safe chars'() {
        when:
        Validations.isSafeBambooString('must "not" be false')

        then:
        Exception e = thrown(DslScriptException)
        e.message.contains('The entry does not contain safe characters')
    }

    def 'safe Bamboo string check with only safe chars'() {
        when:
        Validations.isSafeBambooString('must not, be false')

        then:
        true
    }

    def 'valid Bamboo entity name with only valid chars'() {
        when:
        Validations.isValidBambooEntityName('9The_Project-is-here .', 'error msg')
        then:
        true
    }

    def 'valid Bamboo entity name with invalid chars'() {
        when:
        Validations.isValidBambooEntityName('#/!', 'error msg')
        then:
        Exception e = thrown(DslScriptException)
        e.message.contains('error msg')
    }
}