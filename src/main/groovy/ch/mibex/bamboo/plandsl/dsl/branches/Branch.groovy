package ch.mibex.bamboo.plandsl.dsl.branches

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import ch.mibex.bamboo.plandsl.dsl.Variables
import ch.mibex.bamboo.plandsl.dsl.triggers.Triggers
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class Branch {
    String name
    String description
    boolean enabled = true
    boolean cleanupAutomatically
    Triggers triggers = new Triggers()
    BranchMerging branchMerging
    Variables variables = new Variables()
    NotifyOnNewBranchesType notificationsType = NotifyOnNewBranchesType.NOTIFY_COMMITTERS_FOR_FAVOURITED_BRANCHES
    BranchSourceRepository sourceRepository

    protected Branch() {}

    Branch(String name) {
        Validations.isNotNullOrEmpty(name, 'branch name must be specified')
        Validations.isValidBambooEntityName(name, 'branch name must not contain special characters.')
        this.name = name
    }

    void description(String description) {
        this.description = description
    }

    void enabled(boolean enabled) {
        this.enabled = enabled
    }

    void cleanupAutomatically(boolean cleanupAutomatically) {
        this.cleanupAutomatically = cleanupAutomatically
    }

    void triggers(@DelegatesTo(Triggers) Closure closure) {
        def triggers = new Triggers()
        DslScriptHelper.execute(closure, triggers)
        this.triggers = triggers
    }

    void overridePlansDefaultRepository(@DelegatesTo(BranchSourceRepository) Closure closure) {
        def sourceRepository = new BranchSourceRepository()
        DslScriptHelper.execute(closure, sourceRepository)
        this.sourceRepository = sourceRepository
    }

    void merging(@DelegatesTo(BranchMerging) Closure closure) {
        def branchMerging = new BranchMerging()
        DslScriptHelper.execute(closure, branchMerging)
        this.branchMerging = branchMerging
    }

    void variables(@DelegatesTo(Variables) Closure closure) {
        def variables = new Variables()
        DslScriptHelper.execute(closure, variables)
        this.variables =  variables
    }

    void notifications(NotifyOnNewBranchesType notificationsType) {
        this.notificationsType = notificationsType
    }

    static enum NotifyOnNewBranchesType {
        NOTIFY_COMMITTERS_FOR_FAVOURITED_BRANCHES('notifyCommitters'),
        USE_PLANS_NOTIFICATION_SETTINGS('inherit'),
        DO_NOT_SEND_NOTIFICATIONS('none')

        String bambooNotificationType

        NotifyOnNewBranchesType(String bambooNotificationType) {
            this.bambooNotificationType = bambooNotificationType
        }
    }

}
