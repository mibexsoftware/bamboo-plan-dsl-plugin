package dsls.jobs

import dsls.jobs.commons.MyCommons

def globalJob = job("SIMPLEJOB1", "my simple job")
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

            def myJob2 = job(key: "SIMPLEJOB3", name: "Simple job 3")
            MyCommons.addJobProperties(myJob2, env(), 'myRepo', 'myArtifact', '**/*.jar')

            MyCommons.addJobProperties(globalJob, env(), 'myRepo', 'myArtifact', '**/*.jar')
            job(globalJob)
        }
    }
}
