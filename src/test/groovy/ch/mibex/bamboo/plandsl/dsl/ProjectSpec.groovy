package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.permissions.PermissionTypes
import ch.mibex.bamboo.plandsl.dsl.permissions.Permissions
import spock.lang.Specification

class ProjectSpec extends Specification {

    def 'project with new syntax should yield correct project'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def result = loader.parse(new DslScriptContext(getClass().getResource('/dsls/projects/ProjectNewSyntax.groovy').text))

        then:
        result.projects.size() == 1
        result.projects[0].key == 'SIMPLEPROJECT'
        result.projects[0].name == 'Simple project'
    }

    def 'project with permissions should yield right permissions'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def result = loader.parse(new DslScriptContext(getClass().getResource('/dsls/projects/ProjectWithPermissions.groovy').text))

        then:
        result.projects.size() == 1
        result.projects[0].projectPermissions == new Permissions(
            user: ['diego': [PermissionTypes.PermissionType.CREATE, PermissionTypes.PermissionType.ADMIN]],
            group: ['devops': [PermissionTypes.PermissionType.CREATE]],
            other: [(Permissions.OtherUserType.LOGGED_IN_USERS): [PermissionTypes.PermissionType.CREATE]]
        )
        result.projects[0].planPermissions == new Permissions(
                user: ['paul': [PermissionTypes.PermissionType.ADMIN]],
                group: ['mgmt': [PermissionTypes.PermissionType.EDIT]],
                other: [(Permissions.OtherUserType.LOGGED_IN_USERS): [PermissionTypes.PermissionType.ADMIN, PermissionTypes.PermissionType.EDIT],
                        (Permissions.OtherUserType.ANONYMOUS_USERS): [PermissionTypes.PermissionType.VIEW]]
        )
    }

    def 'project without name should yield exception'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        loader.parse(new DslScriptContext(getClass().getResource('/dsls/projects/InvalidProjectWithoutName.groovy').text))

        then:
        Exception e = thrown(DslScriptException)
        e.message == '(script:5): a project name must be specified'
    }

}