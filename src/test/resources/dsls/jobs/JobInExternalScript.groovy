package dsls.jobs

import dsls.jobs.commons.MyCommons

def globalJob = job("SIMPLEJOB1")
def test = env('test')
project("SIMPLEPROJECT") {
    name "Renamed project"

    plan("SIMPLEPLAN") {
        name "Renamed plan"

        scm {
            linkedRepository("myRepo")
        }

        stage("simple stage") {
            description env('test')
            manual false

            def myJob = job("SIMPLEJOB2")
            MyCommons.addJobProperties(myJob, env(), 'myRepo', 'myArtifact', '**/*.jar')

            MyCommons.addJobProperties(globalJob, env(), 'myRepo', 'myArtifact', '**/*.jar')
            job(globalJob)
        }
    }
}
