---
title: Custom repository
position: 1.9
right_code: |
  ~~~groovy
  scm {
      custom(name: 'TFS', pluginKey: 'com.stellarity.bamboo.tfs-repository-plugin:tfs') {
          configure(
              'stellarity.tfs.repository.url': 'http://localhost:8080/tfs/DefaultCollection',
              'stellarity.tfs.repository.path': '$/test-prj/src',
              'stellarity.tfs.repository.username': 'admin',
              'stellarity.tfs.temporary.passwordChange': true,
              'stellarity.tfs.temporary.password': 'CHANGEIT',
              'stellarity.tfs.repository.removeworkspace': true,
              'stellarity.tfs.repository.versionspec': '1.x',
              'selectedWebRepositoryViewer': 'com.stellarity.bamboo.tfs-repository-plugin:tfsViewer',
              'stellarity.tfs.repository.filter.option': 'INCLUDE',
              'stellarity.tfs.repository.filter.pattern': 'checkout'
          )
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
An example of a custom TFS repository definition.