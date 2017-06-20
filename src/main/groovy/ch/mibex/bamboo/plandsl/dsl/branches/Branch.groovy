package ch.mibex.bamboo.plandsl.dsl.branches

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import ch.mibex.bamboo.plandsl.dsl.triggers.Triggers
import ch.mibex.bamboo.plandsl.dsl.variables.Variables
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Branch extends BambooObject {
    private String name
    private String vcsBranchName
    private String description
    private boolean enabled = true
    private boolean cleanupAutomatically
    private Triggers triggers = new Triggers(bambooFacade)
    private BranchMerging merging
    private Variables variables = new Variables(bambooFacade)
    private NotifyOnNewBranchesType notifications =
            NotifyOnNewBranchesType.NOTIFY_COMMITTERS_FOR_FAVOURITED_BRANCHES
    private BranchSourceRepository sourceRepository

    Branch(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing
    protected Branch() {}

    Branch(String name) {
        Validations.isNotNullOrEmpty(name, 'Plan branch name must be specified')
        Validations.isValidBambooEntityName(name, 'Plan branch name must not contain special characters. ' +
                'Use vcsBranchName for the name of the branch in your repository.')
        this.name = name
    }

    /**
     * The name of the branch in your repository.
     *
     * @param vcsBranchName The repository branch name.
     */
    void vcsBranchName(String vcsBranchName) {
        this.vcsBranchName = vcsBranchName
    }

    /**
     * The description for the new plan branch. Choose a meaningful description for the new branch.
     * For example, "Bamboo tasks feature branch".
     *
     * @param description A description for the new plan branch
     */
    void description(String description) {
        this.description = description
    }

    /**
     * Specifies if the plan branch should be initially enabled or not. If enabled, your branch will be available for
     * building and change detection straight away.
     */
    void enabled(boolean enabled = true) {
        this.enabled = enabled
    }

    /**
     * Specifies if the plan branch should be cleaned up automatically.
     */
    void cleanupAutomatically(boolean cleanupAutomatically = true) {
        this.cleanupAutomatically = cleanupAutomatically
    }

    /**
     * Change the way that Bamboo triggers this plan branch.
     */
    void triggers(@DelegatesTo(value = Triggers, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def triggers = new Triggers(bambooFacade)
        DslScriptHelper.execute(closure, triggers)
        this.triggers = triggers
    }

    /**
     * If you switch this option on, you can override entire repository configuration. When this options is off,
     * the settings shown below are inherited from plan's repository.
     */
    void overridePlansDefaultRepository(
            @DelegatesTo(value = BranchSourceRepository, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def sourceRepository = new BranchSourceRepository(bambooFacade)
        DslScriptHelper.execute(closure, sourceRepository)
        this.sourceRepository = sourceRepository
    }

    /**
     * Enable automatic merging to integrate changes between the branch and the mainline, as well as (optionally)
     * push the merge back to the repository upon a successful integrated build.
     */
    void merging(@DelegatesTo(value = BranchMerging, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def branchMerging = new BranchMerging(bambooFacade)
        DslScriptHelper.execute(closure, branchMerging)
        this.merging = branchMerging
    }

    /**
     * You can override plan variables for this branch. The inherited plan variables and overridden.
     * Values are available on every build run in Bamboo and can be accessed using ${bamboo.variablename}
     */
    void variables(@DelegatesTo(value = Variables, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def variables = new Variables(bambooFacade)
        DslScriptHelper.execute(closure, variables)
        this.variables =  variables
    }

    /**
     * How do you want to be notified about builds for this branch?
     */
    void notifications(NotifyOnNewBranchesType notificationsType) {
        this.notifications = notificationsType
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
