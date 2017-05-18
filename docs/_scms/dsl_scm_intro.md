---
title: Scm (repositories)
position: 1.0
right_code: |
---
`scm` is a collection of repository definition(s) for a plan or plan branch. Here you can see an example for a Github repository definition:

The DSL supports the following repository types:

~~~groovy
void bitbucketCloud(String name, @DelegatesTo(ScmBitbucketCloud) Closure closure)

void bitbucketServer(String name, @DelegatesTo(ScmBitbucketServer) Closure closure)

void git(String name, @DelegatesTo(ScmGit) Closure closure)

void github(String name, @DelegatesTo(ScmGithub) Closure closure)

void subversion(String name, @DelegatesTo(ScmSubversion) Closure closure)

void mercurial(String name, @DelegatesTo(ScmMercurial) Closure closure)

void cvs(String name, @DelegatesTo(ScmCvs) Closure closure)

void perforce(String name, @DelegatesTo(ScmPerforce) Closure closure)

void linkedRepository(String name)
~~~~

For the individual repository definition parameters and their meaning, please see the JavaDoc comments.


