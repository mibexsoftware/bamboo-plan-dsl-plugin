---
title: Variables
position: 1.0
right_code: |
  ~~~groovy
  variables {
      variable 'myKey1', 'myValue1'
      variable 'myKey2', 'myValue2'
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  variables:
    - key: myKey1
      value: myValue1
    - key: myKey2
      value: myValue2
  ~~~
  {: title="YAML" }
---
Variables can be declared in build plans, deployment project environment and plan branches.
