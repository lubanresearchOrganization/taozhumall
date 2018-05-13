


node{
        def customImage

                stage('checkout') {

                checkout scm

                }

                stage('mvn') {
                    docker.image('maven:3-alpine').withRun('-v "$PWD":/usr/src/mymaven -w /usr/src/mymaven'){c ->
                       sh 'mvn -B clean install  -f register/pom.xml'
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