package ch.mibex.bamboo.plandsl.dsl.permissions

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes=['metaClass'])
@ToString(includeFields=true)
class PermissionTypes extends BambooObject {
    private callbackFun

    static enum PermissionType {
        /**
         * Controls who can see this object. For environments, users must have view permission
         * on the deployment project. For plans, users can view the plan in Bamboo, including its builds.
         */
        VIEW('READ'),
        /**
         * Controls who can edit this object. For deployment projects, users with edit permission can
         * also create releases. For plans, user can view and edit the configuration of the plan and it's jobs.
         * This does not include the ability to change a plan's permissions or its stages.
         */
        EDIT('WRITE'),
        /**
         * User can trigger a manual build on the plan, as well as suspending and resuming the plan.
         */
        BUILD('BUILD'),
        /**
         * User can clone the plan.
         */
        CLONE('CLONE'),
        /**
         * User can administrate all components of this plan including the stages and the plan's permissions.
         */
        ADMIN('ADMINISTRATION'),
        /**
         * Defines who can releases for this project and who can deploy them to this environment.
         */
        DEPLOY('BUILD')

        private final String internalName

        PermissionType(String internalName) {
            this.internalName = internalName
        }
    }

    // just for testing
    protected PermissionTypes() {}

    protected PermissionTypes(BambooFacade bambooFacade, callbackFun) {
        super(bambooFacade)
        this.callbackFun = callbackFun
    }

    /**
     * The permission types.
     *
     * @param permissionTypes List of permission types
     */
    void permissionTypes(PermissionType... permissionTypes) {
        callbackFun(permissionTypes)
    }
}
