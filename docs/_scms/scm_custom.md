---
title: Custom repository
position: 1.9
right_code: |
  ~~~groovy
  scm {
      custom(name: 'MYREPO', pluginKey: 'com.stellarity.bamboo.tfs-repository-plugin:tfs') {
          configure(
              'stellarity.tfs.repository.url': 'https://server/DefaultCollection',
              'stellarity.tfs.repository.path': "\$/proj/SimpleDriver/TheSimpliest",
              'stellarity.tfs.repository.username': 'username',
              'stellarity.tfs.repository.password': encryptionService.encrypt('password'),
              'stellarity.tfs.repository.removeworkspace': true,
              'stellarity.tfs.repository.versionspec': '',
              'selectedWebRepositoryViewer': 'com.stellarity.bamboo.tfs-repository-plugin:tfsViewer',
              'stellarity.tfs.repository.filter.option': 'INCLUDE',
              'stellarity.tfs.repository.filter.pattern': '.*\\.h'
          )
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  scm
    - !customScm
      name: TFS
      pluginKey: com.stellarity.bamboo.tfs-repository-plugin:tfs
      config:
        stellarity.tfs.repository.url: 'https://server/DefaultCollection'
        stellarity.tfs.repository.path: '$/proj/SimpleDriver/TheSimpliest'
        stellarity.tfs.repository.username: username
        stellarity.tfs.temporary.password: !encrypt password
        stellarity.tfs.repository.removeworkspace: true
        stellarity.tfs.repository.versionspec:
        selectedWebRepositoryViewer: 'com.stellarity.bamboo.tfs-repository-plugin:tfsViewer'
        stellarity.tfs.repository.filter.option: INCLUDE
        stellarity.tfs.repository.filter.pattern: '.*\\.h'
  ~~~
  {: title="YAML" }
---
Custom repositories provide a way to use plug-in provided repository types. Here you can see an example for the
TFS repository.