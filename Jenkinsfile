


node{
        def customImage

                stage('checkout') {

                checkout scm

                }

                stage('maven') {
                    docker.image('maven:3.5.2-jdk-8-alpine'){
                        sh 'mvn clean install -f register/pom.xml'
                    }

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