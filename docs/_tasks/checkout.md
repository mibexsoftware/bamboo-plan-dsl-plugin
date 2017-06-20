---
title: Checkout
position: 1.4
right_code: |
  ~~~groovy
  tasks {
      checkout() {
          description 'checkout repo'
          forceCleanBuild true

          repository(name: 'Bamboo Plan DSL') {
              checkoutDirectory 'src'
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  tasks:
    - !checkout
      description: checkout repo
      forceCleanBuild: true
      repositories:
        - name: Bamboo Plan DSL
          checkoutDirectory: src
  ~~~
  {: title="YAML" }
---
A task to checkout a repository to a working directoy.