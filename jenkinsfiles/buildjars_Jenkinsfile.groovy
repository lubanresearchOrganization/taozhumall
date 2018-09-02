node {

    stage('checkout') {

        checkout scm
    }

    stage('maven') {

        docker.image('maven:3.5.2-jdk-8-alpine').inside('-v /opt/data/maven/.m2/:/root/.m2/'){
            sh 'mvn clean install '
        }
    }

}
