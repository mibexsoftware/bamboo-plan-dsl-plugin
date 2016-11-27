package ch.mibex.bamboo.plandsl.dsl.branches

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject

class MergeStrategy extends BambooObject {
    protected String planBranchKey
    protected boolean pushEnabled

    MergeStrategy(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing
    protected MergeStrategy() {}

    /**
     * Select Push on only if you want those changes merged back into your branch once the build completes successfully,
     */
    void pushEnabled(boolean enabled = true) {
        this.pushEnabled = enabled
    }
}
