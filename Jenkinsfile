

pipeline {

    def customImage
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    stages {
        stage('checkout') {

        checkout scm

        }

        stage('mvn') {

            steps {
                sh 'mvn -B clean install  -f register/pom.xml'
            }
        }

        stage('dockerbuild') {

            def customImage = docker.build("register:${env.BUILD_ID}")
        }

        stage('dockerpush') {

            docker.withRegistry('registry.cn-hangzhou.aliyuncs.com/hilbertcao/hilbertcao') {

                        customImage.push()
                    }
            }
        }
    }
}