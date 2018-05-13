node {
    def dockerImage
    def moduleName = 'nginxserver'
    def registryUrl = 'http://registry.lubanresearch.com:5000'
    def dockerVersion = '0.1'

    stage('checkout') {

        checkout scm
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
