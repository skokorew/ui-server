properties([pipelineTriggers([pollSCM('H/1 * * * *')])])

node {
    def artifactory = Artifactory.server "local_artifactory"
    def rtMaven = Artifactory.newMavenBuild()
    def buildInfo

    stage ('Checkout') {
        checkout scm
    }

    stage('SonarQube analysis') {
        withSonarQubeEnv('local_sonar') {
            sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.0.1398:sonar'
        }
    }

    stage ('Configure') {
        rtMaven.tool = 'local_mvn'
        rtMaven.deployer releaseRepo: 'ext-release-local', snapshotRepo: 'ext-snapshot-local', server: artifactory
        rtMaven.resolver releaseRepo: 'maven-central', snapshotRepo: 'maven-central', server: artifactory
    }

    stage ('Build') {
        buildInfo = rtMaven.run pom: 'pom.xml', goals: 'clean install'
    }

    stage ('Test') {
        junit allowEmptyResults: true, testResults:'target/surefire-reports/*.xml'
    }

    stage ('Publish build info') {
        artifactory.publishBuildInfo buildInfo
    }

    stage ('Build image') {
        rtMaven.run pom: 'pom.xml', goals: 'dockerfile:build'
    }

}