

pipeline {

                   agent {
                        docker {
                            image 'maven:3-alpine'
                            args '-v /root/.m2:/root/.m2'
                        }
                    }

node{
        def customImage

stages{
                stage('checkout') {

                checkout scm

                }

                stage('maven') {


                        sh 'mvn clean install -f register/pom.xml'

                }

                stage('dockerbuild') {

                    customImage = docker.build("register:${env.BUILD_ID}")
                }

                stage('dockerpush') {

                    docker.withRegistry('registry.cn-hangzhou.aliyuncs.com/hilbertcao/hilbertcao') {

                         customImage.push()
                    }
                }


}
}

}