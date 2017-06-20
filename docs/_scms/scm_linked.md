---
title: Linked repository
position: 1.8
right_code: |
  ~~~groovy
  scm {
      linkedRepository(name: "myGlobalRepo")
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  scm:
    - !linkedRepository
      name: myGlobalRepo
  ~~~
  {: title="YAML" }
---
A definition for a linked Bamboo repository. Linked repositories are global to the Bamboo installation.