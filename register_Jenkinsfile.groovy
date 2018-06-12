node {
    def dockerImage
    def moduleName = 'register'
    def pomName = 'pom.xml'
    def registryUrl = 'http://registry.lubanresearch.com:5000'
    def dockerVersion = '0.1'

    stage('checkout') {

        checkout scm
    }

    stage('maven') {

        docker.image('maven:3.5.2-jdk-8-alpine').inside('-v /opt/data/maven/.m2/:/root/.m2/'){
            sh 'mvn clean package -f ' + moduleName + '/' + pomName
        }
    }

    stage('dockerbuild') {

        dockerImage = docker.build(moduleName + ':' + dockerVersion, moduleName)
    }

    stage('dockerpush') {

        docker.withRegistry(registryUrl) {

            dockerImage.push()
        }
    }

    stage('update'){

        sh 'docker service update --force --image=registry.lubanresearch.com:5000/'+moduleName+':'+dockerVersion+' taozhumall_'+moduleName+' '
    }

}
