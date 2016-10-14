package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import ch.mibex.bamboo.plandsl.dsl.scm.auth.*
import ch.mibex.bamboo.plandsl.dsl.scm.options.*
import ch.mibex.bamboo.plandsl.dsl.scm.web.BitbucketWebRepository
import ch.mibex.bamboo.plandsl.dsl.scm.web.FisheyeWebRepository
import ch.mibex.bamboo.plandsl.dsl.scm.web.WebRepository
import spock.lang.Specification

class ScmsSpec extends Specification {

    def 'plan with Git SCM'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/scms/GitPassword.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].scm.children()[0] == new ScmGit(
            advancedOptions: new AdvancedGitOptions(
                    useShallowClones: true,
                    enableRepositoryCachingOnRemoteAgents: true,
                    useSubmodules: true,
                    commandTimeoutInMinutes: 20,
                    verboseLogs: true,
                    fetchWholeRepository: true,
                    quietPeriod: new QuietPeriod(
                        waitTimeInSeconds: 120,
                        maximumRetries: 3
                    ),
                    includeExcludeFiles: new IncludeExcludeFiles(
                        matchType: ScmType.MatchType.EXCLUDE_ALL_MATCHING_CHANGES,
                        filePattern: 'exe'
                    ),
                    excludeChangesetsRegex: 'FIXES .*',
                    webRepository: new WebRepository(
                        type: new FisheyeWebRepository(
                                  url: "http://localhost:7990",
                                  repositoryPath: "a/b/c",
                                  repositoryName: "d"
                              )
                    )
            ),
            displayName: "myGitRepo",
            url: "http://localhost:7990/bitbucket/scm/project_1/java-maven-simple.git",
            branch: "master",
            authType: new PasswordAuth(userName: "admin", password: "pw")
        )
    }

    def 'plan with Bitbucket Server SCM'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/scms/BitbucketServer.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].scm.children()[0] == new ScmBitbucketServer(
                displayName: "myBitbucketServerRepo",
                projectKey: "project_1",
                repoSlug: "rep_1",
                repoId: "1",
                branch: "develop",
                repositoryUrl: "ssh://git@michis-mbp:7999/project_1/rep_1.git",
                serverName: "bitbucketServer",
                advancedOptions: new AdvancedGitRepoOptions(
                        useShallowClones: true,
                        useSubmodules: true,
                        commandTimeoutInMinutes: 20,
                        verboseLogs: true,
                        fetchWholeRepository: true,
                        quietPeriod: new QuietPeriod(
                                waitTimeInSeconds: 120,
                                maximumRetries: 3
                        ),
                        includeExcludeFiles: new IncludeExcludeFiles(
                                matchType: ScmType.MatchType.EXCLUDE_ALL_MATCHING_CHANGES,
                                filePattern: 'exe'
                        ),
                        excludeChangesetsRegex: 'FIXES .*',
                        webRepository: new WebRepository(
                                type: new FisheyeWebRepository(
                                        url: "http://localhost:7990",
                                        repositoryPath: "a/b/c",
                                        repositoryName: "d"
                                )
                        )
                )
        )
    }

    def 'plan with Bitbucket Cloud Git SCM'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/scms/BitbucketGitPassword.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].scm.children()[0] == new ScmBitbucketCloud(
            displayName: "myBitbucketGitRepo",
            repoSlug: "project_1/java-maven-simple",
            branch: "develop",
            authType: new PasswordAuth(userName: "admin", password: "pw"),
            scmType: new ScmBitbucketGit(
                    advancedOptions: new AdvancedGitRepoOptions(
                            useShallowClones: true,
                            useSubmodules: true,
                            commandTimeoutInMinutes: 20,
                            verboseLogs: true,
                            fetchWholeRepository: true,
                            quietPeriod: new QuietPeriod(
                                    waitTimeInSeconds: 120,
                                    maximumRetries: 3
                            ),
                            includeExcludeFiles: new IncludeExcludeFiles(
                                    matchType: ScmType.MatchType.EXCLUDE_ALL_MATCHING_CHANGES,
                                    filePattern: 'exe'
                            ),
                            excludeChangesetsRegex: 'FIXES .*',
                            webRepository: new WebRepository(
                                    type: new FisheyeWebRepository(
                                            url: "http://localhost:7990",
                                            repositoryPath: "a/b/c",
                                            repositoryName: "d"
                                    )
                            )
                    )
            )
        )
    }

    def 'plan with Bitbucket Cloud Mercurial SCM'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/scms/BitbucketHgPassword.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].scm.children()[0] == new ScmBitbucketCloud(
                displayName: "myBitbucketGhRepo",
                repoSlug: "project_1/java-maven-simple",
                branch: "master",
                authType: new PasswordAuth(userName: "user", password: "pw"),
                scmType: new ScmBitbucketHg(
                        advancedOptions: new AdvancedHgBitbucketOptions(
                                commandTimeoutInMinutes: 20,
                                verboseLogs: true,
                                fetchWholeRepository: true,
                                quietPeriod: new QuietPeriod(
                                        waitTimeInSeconds: 120,
                                        maximumRetries: 3
                                ),
                                includeExcludeFiles: new IncludeExcludeFiles(
                                        matchType: ScmType.MatchType.EXCLUDE_ALL_MATCHING_CHANGES,
                                        filePattern: 'exe'
                                ),
                                excludeChangesetsRegex: 'FIXES .*',
                                webRepository: new WebRepository(
                                        type: new FisheyeWebRepository(
                                                url: "http://localhost:7990",
                                                repositoryPath: "a/b/c",
                                                repositoryName: "d"
                                        )
                                )
                        )
                )
        )
    }

    def 'plan with Github SCM'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/scms/GithubPassword.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].scm.children()[0] == new ScmGithub(
            advancedOptions: new AdvancedGitOptions(
                    useShallowClones: true,
                    enableRepositoryCachingOnRemoteAgents: true,
                    useSubmodules: true,
                    commandTimeoutInMinutes: 20,
                    verboseLogs: true,
                    fetchWholeRepository: true,
                    quietPeriod: new QuietPeriod(
                            waitTimeInSeconds: 120,
                            maximumRetries: 3
                    ),
                    includeExcludeFiles: new IncludeExcludeFiles(
                            matchType: ScmType.MatchType.EXCLUDE_ALL_MATCHING_CHANGES,
                            filePattern: 'exe'
                    ),
                    excludeChangesetsRegex: 'FIXES .*',
                    webRepository: new WebRepository(
                            type: new FisheyeWebRepository(
                                    url: "http://localhost:7990",
                                    repositoryPath: "a/b/c",
                                    repositoryName: "d"
                            )
                    )
            ),
            displayName: "myGithubRepo",
            repoSlug: "test/HelloWorld",
            branch: "master",
            authType: new PasswordAuth(userName: "test", password: "pw")
        )
    }

    def 'plan with linked repository'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/scms/LinkedRepository.groovy').text
        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].scm.children()[0] == new ScmLinkedRepository(displayName: "myGlobalRepo1")
    }

    def 'plan with Mercurial SCM'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/scms/MercurialDefaultCredentials.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].scm.children()[0] == new ScmMercurial(
                displayName: "myHg",
                repositoryUrl: "http://hg.red-bean.com/repos/test",
                branch: "master",
                authType: new DefaultMercurialAuth(),
                advancedOptions: new AdvancedHgMercurialOptions(
                        enableCommitIsolation: true,
                        commandTimeoutInMinutes: 180,
                        verboseLogs: true,
                        disableRepositoryCaching: false,
                        quietPeriod: new QuietPeriod(
                            waitTimeInSeconds: 120,
                            maximumRetries: 3
                        ),
                        includeExcludeFiles: new IncludeExcludeFiles(
                            matchType: ScmType.MatchType.EXCLUDE_ALL_MATCHING_CHANGES,
                            filePattern: 'exe'
                        ),
                        excludeChangesetsRegex: 'FIXES .*',
                        webRepository: new WebRepository(type: new BitbucketWebRepository())
                )
        )
    }

    def 'plan with Subversion password SCM'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/scms/SvnPassword.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].scm.children()[0] == new ScmSubversion(
                advancedOptions: new AdvancedSvnOptions(
                        detectChangesInExternals: true,
                        useSvnExport: true,
                        enableCommitIsolation: true,
                        autoDetectRootUrlForBranches: false,
                        branchesRootUrl: "/branches",
                        autoDetectRootUrlForTags: false,
                        tagRootUrl: "/tags",
                        quietPeriod: new QuietPeriod(
                                waitTimeInSeconds: 120,
                                maximumRetries: 3
                        ),
                        includeExcludeFiles: new IncludeExcludeFiles(
                                matchType: ScmType.MatchType.EXCLUDE_ALL_MATCHING_CHANGES,
                                filePattern: 'exe'
                        ),
                        excludeChangesetsRegex: 'FIXES .*',
                        webRepository: new WebRepository(
                                type: new FisheyeWebRepository(
                                        url: "http://localhost:7990",
                                        repositoryPath: "a/b/c",
                                        repositoryName: "d"
                                )
                        )
                ),
                repositoryUrl: "http://svn.red-bean.com/repos/test",
                userName: "admin",
                authType: new PasswordAuth(userName: "admin", password: "pw")
        )
    }

    def 'plan with Subversion SSH SCM'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/scms/SvnSsh.groovy').text
        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].scm.children()[0] == new ScmSubversion(
                advancedOptions: new AdvancedSvnOptions(
                        detectChangesInExternals: true,
                        enableCommitIsolation: false,
                        autoDetectRootUrlForBranches: true,
                        autoDetectRootUrlForTags: true,
                        webRepository: new WebRepository(
                                type: new FisheyeWebRepository(
                                        url: "http://localhost:7990",
                                        repositoryPath: "a/b/c",
                                        repositoryName: "d"
                                )
                        )
                ),
                displayName: 'mySvn',
                repositoryUrl: "http://svn.red-bean.com/repos/test",
                userName: "admin",
                authType: new SshAuth(passPhrase: "pw", privateKey: "-----BEGIN RSA PRIVATE KEY-----\n" +
                        "Proc-Type: 4,ENCRYPTED\n" +
                        "-----END RSA PRIVATE KEY-----")
        )
    }

    def 'plan with Subversion SSL SCM'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/scms/SvnSslClientCertificate.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].scm.children()[0] == new ScmSubversion(
                repositoryUrl: "http://svn.red-bean.com/repos/test",
                userName: "admin",
                authType: new SslClientCertificateAuth(passPhrase: "pw", privateKey: "/a/b/c.key")
        )
    }

    def 'plan with CVS SCM'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/scms/CvsPassword.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].scm.children()[0] == new ScmCvs(
                advancedOptions: new AdvancedCvsOptions(
                        includeExcludeFiles: new IncludeExcludeFiles(
                                matchType: ScmType.MatchType.INCLUDE_ONLY_MATCHING_CHANGES,
                                filePattern: 'bat'
                        ),
                        excludeChangesetsRegex: 'FIXES .*',
                        webRepository: new WebRepository(
                                type: new FisheyeWebRepository(
                                        url: "http://localhost:7990",
                                        repositoryPath: "a/b/c",
                                        repositoryName: "d"
                                )
                        )
                ),
                cvsRoot: "http://localhost:7990/bitbucket/scm/project_1/java-maven-simple.cvs",
                quietPeriodInSeconds: 60,
                module: "test",
                displayName: "myCvsRepo",
                moduleVersion: ScmCvs.CvsModuleVersion.HEAD,
                authType: new PasswordAuth(password: "pw")
        )
    }

    def 'plan with Perforce SCM'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/scms/PerforcePassword.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].scm.children()[0] == new ScmPerforce(
                advancedOptions: new AdvancedPerforceOptions(
                        includeExcludeFiles: new IncludeExcludeFiles(
                                matchType: ScmType.MatchType.EXCLUDE_ALL_MATCHING_CHANGES,
                                filePattern: 'exe'
                        ),
                        excludeChangesetsRegex: 'FIXES .*',
                        webRepository: new WebRepository(
                                type: new FisheyeWebRepository(
                                        url: "http://localhost:7990",
                                        repositoryPath: "a/b/c",
                                        repositoryName: "d"
                                )
                        ),
                        quietPeriod: new QuietPeriod(
                                waitTimeInSeconds: 120,
                                maximumRetries: 3
                        )
                ),
                displayName: "myPerforceRepo",
                port: "9091",
                client: "perforce",
                depotView: "//perforce/workspace",
                environmentVariables: "P4CHARSET=\"utf8\"",
                letBambooManageWorkspace: true,
                useClientMapping: true,
                passwordAuth: new PasswordAuth(userName: "admin", password: "pw")
        )
    }

}