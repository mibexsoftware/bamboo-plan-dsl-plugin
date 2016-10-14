package ch.mibex.bamboo.plandsl.dsl.transform

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import spock.lang.Specification

// inspired by Jenkins job DSL plug-in
class RequiresPluginCheckAstTransformationSpec extends Specification {
    BambooFacade requirements = Mock(BambooFacade)
    TestPlan testPlan = new TestPlan(requirements)

    def 'requires sonar for bamboo plug-in'() {
        when:
        testPlan.requiresSonar4BambooPlugin()

        then:
        1 * requirements.requirePlugin('ch.mibex.bamboo.sonar4bamboo')
    }

    def 'requires shipit plug-in >= 1.2.0'() {
        when:
        testPlan.requiresShipItPluginWithMinVersion()

        then:
        1 * requirements.requireMinimumPluginVersion('ch.mibex.bamboo.shipit', '1.2.0')
    }
}