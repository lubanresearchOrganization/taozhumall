


node{
        def customImage

                stage('checkout') {

                checkout scm
                sh 'echo address $PWD'
                }

                stage('maven') {

                        sh 'echo $PWD'
                        sh 'docker run --rm --name taozhumall -v "${WORKSPACE}":/usr/src/mymaven -v /opt/data/maven/.m2/:/root/.m2/ -w /usr/src/mymaven maven:3.5.2-jdk-8-alpine mvn clean install'

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
