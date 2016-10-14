package ch.mibex.bamboo.plandsl.dsl.transform

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import spock.lang.Specification

// inspired by Jenkins job DSL plug-in
class BambooMinVersionCheckAstTransformationSpec extends Specification {
    BambooFacade requirements = Mock(BambooFacade)
    TestPlan testPlan = new TestPlan(requirements)

    def 'requires Bamboo minimum version'() {
        when:
        testPlan.requiresBambooVersion()

        then:
        1 * requirements.requireBambooVersion('5.12')
    }

}