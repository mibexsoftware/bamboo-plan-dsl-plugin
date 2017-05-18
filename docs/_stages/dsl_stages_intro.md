---
title: Stage Definition
position: 1.0
description: Not working!!!
right_code: |
  ~~~ groovy
  stage(name: 'stage1') {
    description 'this is a simple stage'
    manual false

    job(key: 'ANALYZE', name: 'analyze the software') {
    }
    job(key: 'BUILD', name: 'build it') {
    }
    // more jobs...
  }
  ~~~
  {: title="DSL" }

---
A stage consists of a name, a description, a manual field and multiple jobs.

