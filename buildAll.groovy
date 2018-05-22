#!/usr/bin/env groovy
def jenkinsFile
stage('excuting baseservice_Jenkinsfile.groovy') {
    baseserviceJenkinsfile = fileLoader.from("baseservice_Jenkinsfile.groovy");
    baseserviceJenkinsfile.start();
}
stage('excuting baseui_Jenkinsfile.groovy') {
    baseuiJenkinsfile = fileLoader.from("baseui_Jenkinsfile.groovy");
    baseuiJenkinsfile.start();
}