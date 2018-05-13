node {
    def dockerImage
    def moduleName = 'baseui'
    def pomName = 'pom_image.xml'
    def registryUrl = 'http://registry.lubanresearch.com:5000'
    def dockerVersion = '0.1'

    stage('maven') {

        sh 'docker run --rm --name maven  -v "/opt/data/jenkins_home/workspace/${JOB_NAME}":/mvn_build -v /opt/data/maven/.m2/:/root/.m2/ -w /mvn_build maven:3.5.2-jdk-8-alpine mvn clean install -f '+moduleName+'/'+pomName

    }

    stage('dockerbuild') {

        dockerImage = docker.build(moduleName+':'+dockerVersion, moduleName)
    }

    stage('dockerpush') {

        docker.withRegistry(registryUrl) {

            dockerImage.push()
        }
    }

}
