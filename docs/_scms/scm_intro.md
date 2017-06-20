---
title: SCM overview
position: 1.0
right_code: |
  ~~~ groovy
  plan(key: 'PLAN1KEY', name: 'my plan 1') {
      scm {
          // repository definitions
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  plans:
    - key: PLAN1KEY
      name: my plan 1
      scm:
        # repository definitions
  ~~~
  {: title="YAML" }
---
SCM is a collection of repository definition(s) for a plan or plan branch. The first repository in the list is
automatically the default one.

The DSL supports the following repository types:

  - [Bitbucket Cloud](#scm_bitbucket_cloud)
  - [Bitbucket Server](#scm_bbs)
  - [Plain Git](#scm_git)
  - [Github](#scm_github)
  - [Subversion](#scm_subversion)
  - [Mercurial](#scm_mercurial)
  - [CVS](#scm_cvs)
  - [Perforce](#scm_perforce)
  - [Linked repositories](#scm_linked)
  - [Custom repositories](#scm_custom)
  
Custom repositories allow you to support repositories which are not built-in into Bamboo, but are instead provided by
 a plug-in (e.g., the TFS repository type).
