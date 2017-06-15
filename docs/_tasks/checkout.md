---
title: Checkout
position: 1.4
right_code: |
  ~~~groovy
  tasks {
      checkout() {
          description 'checkout repo'
          forceCleanBuild true

          repository(name: 'Bamboo Job DSL') {
              checkoutDirectory 'src'
          }
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml       
  ~~~
  {: title="YAML" }
---
A task.