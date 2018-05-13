node {
    def dockerImage
    def moduleName = 'baseservice'
    def pomName = 'pom_image.xml'
    def registryUrl = 'http://registry.lubanresearch.com:5000'
    def dockerVersion = '0.1'
    def mountBuildDir = "/opt/data/jenkins_home/workspace/${JOB_NAME}"
    def mountMvnDir = "/opt/data/maven/.m2/"

    stage('checkout') {

        checkout scm
    }

    stage('maven') {

        sh 'docker run --rm --name maven  -v ' + mountBuildDir + ':/mvn_build -v ' + mountMvnDir + ':/root/.m2/ -w /mvn_build maven:3.5.2-jdk-8-alpine mvn clean install -f ' + moduleName + '/' + pomName

    }

    stage('dockerbuild') {

        dockerImage = docker.build(moduleName + ':' + dockerVersion, moduleName)
    }

    stage('dockerpush') {

        docker.withRegistry(registryUrl) {

            dockerImage.push()
        }
    }

}
