


node{
        def customImage

                stage('checkout') {

                checkout scm

                }

                stage('mvn') {
                    docker.image('maven:3-alpine').withRun('-v $HOME/.m2:/root/.m2'){
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