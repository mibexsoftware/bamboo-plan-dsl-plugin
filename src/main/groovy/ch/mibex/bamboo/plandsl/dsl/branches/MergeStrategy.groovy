package ch.mibex.bamboo.plandsl.dsl.branches

class MergeStrategy {
    String planBranchKey
    boolean pushEnabled

    void pushEnabled(boolean enabled = true) {
        this.pushEnabled = enabled
    }
}
