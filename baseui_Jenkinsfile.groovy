node {
    def customImage

    stage('checkout') {

        checkout scm
        sh 'echo address $PWD'
    }

    stage('maven') {

        sh 'echo $PWD'
        sh 'docker run --rm --name taozhumall  -v "/opt/data/jenkins_home/workspace/${JOB_NAME}":/usr/src/mymaven -v /opt/data/maven/.m2/:/root/.m2/ -w /usr/src/mymaven maven:3.5.2-jdk-8-alpine mvn clean install -f baseui/pom_image.xml'

    }

    stage('dockerbuild') {

        customImage = docker.build("baseui:0.1", "${workdir}/baseui")
    }

    stage('dockerpush') {

        docker.withRegistry('http://registry.lubanresearch.com:5000') {

            customImage.push()
        }
    }


}
