node {
    def customImage
    def moduleName = 'baseservice'
    def pomName = 'pom_image.xml'

    stage('maven') {

        sh 'docker run --rm --name taozhumall  -v "/opt/data/jenkins_home/workspace/${JOB_NAME}":/usr/src/mymaven -v /opt/data/maven/.m2/:/root/.m2/ -w /usr/src/mymaven maven:3.5.2-jdk-8-alpine mvn clean install -f '+moduleName+'/'+pomName

    }

    stage('dockerbuild') {

        customImage = docker.build(moduleName+':0.1', moduleName)
    }

    stage('dockerpush') {

        docker.withRegistry('http://registry.lubanresearch.com:5000') {

            customImage.push()
        }
    }


}
