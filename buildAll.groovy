pipeline {
    agent
            {
                node {
                    label 'master'
                    customWorkspace "${env.JobPath}"
                }
            }

    stages
            {

                stage ('baseservice') {
                    steps {
                        build job: 'baseservice', parameters: [
                        ]
                    }
                }

                stage ('baseui') {
                    steps {
                        build job: 'baseservice', parameters: [
                        ]
                    }
                }
            }
}