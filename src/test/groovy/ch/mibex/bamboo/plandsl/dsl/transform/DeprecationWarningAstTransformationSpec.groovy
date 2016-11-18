package ch.mibex.bamboo.plandsl.dsl.transform

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import spock.lang.Specification

// inspired by Jenkins job DSL plug-in
class DeprecationWarningAstTransformationSpec extends Specification {
    BambooFacade bambooFacade = Mock(BambooFacade)
    TestPlan testPlan = new TestPlan(bambooFacade)

    def 'logs deprecation warning'() {
        when:
        testPlan.deprecated()

        then:
        1 * bambooFacade.log(_)
    }

}