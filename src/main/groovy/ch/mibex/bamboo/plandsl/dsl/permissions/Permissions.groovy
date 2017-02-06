package ch.mibex.bamboo.plandsl.dsl.permissions

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Permissions extends BambooObject {
    private Map<String, PermissionTypes> userPermissions = [:]
    private Map<String, PermissionTypes> groupPermissions = [:]
    private Map<String, PermissionTypes> otherPermissions = [:]

    protected Permissions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing
    protected Permissions() {}

    static enum OtherUserType {
        LOGGED_IN_USERS('ROLE_USER'),
        ANONYMOUS_USERS('ROLE_ANONYMOUS')

        private String internalName

        OtherUserType(String internalName) {
            this.internalName = internalName
        }
    }

    /**
     * Permissions for a specific user.
     *
     * @param params A collection of properties. Currently only "name" is supported.
     */
    void user(Map<String, String> params, @DelegatesTo(PermissionTypes) Closure closure) {
        user(params['name'], closure)
    }

    /**
     * Permissions for a specific user.
     *
     * @param name Name of the user
     */
    void user(String name, @DelegatesTo(PermissionTypes) Closure closure) {
        def permissionTypes = new PermissionTypes(bambooFacade)
        DslScriptHelper.execute(closure, permissionTypes)
        userPermissions[name] = permissionTypes
    }

    /**
     * Permissions for a specific group.
     *
     * @param params A collection of properties. Currently only "name" is supported.
     */
    void group(Map<String, String> params, @DelegatesTo(PermissionTypes) Closure closure) {
        group(params['name'], closure)
    }

    /**
     * Permissions for a specific group.
     *
     * @param name Name of the group
     */
    void group(String name, @DelegatesTo(PermissionTypes) Closure closure) {
        def permissionTypes = new PermissionTypes(bambooFacade)
        DslScriptHelper.execute(closure, permissionTypes)
        groupPermissions[name] = permissionTypes
    }

    /**
     * Permissions for other user types.
     *
     * @param params A collection of properties. Currently only "type" is supported.
     */
    void other(Map<String, Object> params, @DelegatesTo(PermissionTypes) Closure closure) {
        other(params['type'] as OtherUserType, closure)
    }

    /**
     * Permissions for another user type.
     *
     * @param otherUserType user type
     */
    void other(OtherUserType otherUserType, @DelegatesTo(PermissionTypes) Closure closure) {
        def permissionTypes = new PermissionTypes(bambooFacade)
        DslScriptHelper.execute(closure, permissionTypes)
        otherPermissions[otherUserType.internalName] = permissionTypes
    }
}
