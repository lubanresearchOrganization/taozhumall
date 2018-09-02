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
                        build job: 'baseui', parameters: [
                        ]
                    }
                }

                stage ('userservice') {
                    steps {
                        build job: 'userservice', parameters: [
                        ]
                    }
                }

                stage ('categoryservice') {
                    steps {
                        build job: 'categoryservice', parameters: [
                        ]
                    }
                }


                stage ('commentservice') {
                    steps {
                        build job: 'commentservice', parameters: [
                        ]
                    }
                }


                stage ('searchservice') {
                    steps {
                        build job: 'searchservice', parameters: [
                        ]
                    }
                }


                stage ('merchantservice') {
                    steps {
                        build job: 'merchantservice', parameters: [
                        ]
                    }
                }


                stage ('orderservice') {
                    steps {
                        build job: 'orderservice', parameters: [
                        ]
                    }
                }


                stage ('cart') {
                    steps {
                        build job: 'cart', parameters: [
                        ]
                    }
                }


                stage ('usercenter') {
                    steps {
                        build job: 'usercenter', parameters: [
                        ]
                    }
                }


                stage ('platformui') {
                    steps {
                        build job: 'platformui', parameters: [
                        ]
                    }
                }

                stage ('shopui') {
                    steps {
                        build job: 'shopui', parameters: [
                        ]
                    }
                }


                stage ('customerui') {
                    steps {
                        build job: 'customerui', parameters: [
                        ]
                    }
                }


                stage ('nginxserver') {
                    steps {
                        build job: 'nginxserver', parameters: [
                        ]
                    }
                }
            }
}