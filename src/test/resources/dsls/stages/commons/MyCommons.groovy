package dsls.stages.commons

import ch.mibex.bamboo.plandsl.dsl.Stage

class MyCommons {

    static void addStageProperties(Stage stage) {
        stage.with {
            description "this was a simple stage"
            manual true

            job(key: "SIMPLEJOB", name: "Simple job") {
                description "This is a simple job"
                enabled true

                tasks {
                    script('echo hello world') {
                    }
                }
            }
        }
    }

}