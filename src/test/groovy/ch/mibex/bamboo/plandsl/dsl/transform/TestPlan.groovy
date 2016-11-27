package ch.mibex.bamboo.plandsl.dsl.transform

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.Plan
import ch.mibex.bamboo.plandsl.dsl.RequiresBambooVersion
import ch.mibex.bamboo.plandsl.dsl.RequiresPlugin

class TestPlan extends Plan {
    TestPlan(BambooFacade bambooFacade) {
        super('PLANKEY', bambooFacade)
    }

    @Deprecated
    void deprecated() {
    }

    @RequiresBambooVersion(minimumVersion = "5.12")
    void requiresBambooVersion() {
    }

    @RequiresPlugin(key = "ch.mibex.bamboo.sonar4bamboo")
    void requiresSonar4BambooPlugin() {
    }

    @RequiresPlugin(key = "ch.mibex.bamboo.shipit", minimumVersion = "1.2.0")
    void requiresShipItPluginWithMinVersion() {
    }
}
