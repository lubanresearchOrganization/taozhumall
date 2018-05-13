


node{
        def customImage

                stage('checkout') {

                checkout scm

                }

                stage('maven') {
                    docker.image('maven:3.5.2-jdk-8-alpine').withRun('-it --rm --name lubanmall -v "$PWD":/usr/src/mymaven -w /usr/src/mymaven mvn clean install register/pom.xml')
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