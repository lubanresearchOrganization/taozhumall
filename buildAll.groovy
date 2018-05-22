node {
    stage('excuting baseservice_Jenkinsfile.groovy') {
        baseserviceJenkinsfile = load "baseservice_Jenkinsfile.groovy"
        baseserviceJenkinsfile.start()
    }
    stage('excuting baseui_Jenkinsfile.groovy') {
        baseuiJenkinsfile = load "baseui_Jenkinsfile.groovy"
        baseuiJenkinsfile.start();
    }

}