node {
    def artifactory = Artifactory.server "local_artifactory"
    def rtMaven = Artifactory.newMavenBuild()
    def buildInfo

    stage ('Checkout') {
        checkout scm
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
        //junit allowEmptyResults: true, testResults: 'server-mb_master/target/surefire-reports/*'
        //withMaven(jdk: 'local_jdk', maven: 'local_mvn', options: [junitPublisher(healthScaleFactor: 1.0)]) {
            // some block
        //}
        junit 'target/surefire-reports/*.xml'
    }

    stage ('Publish build info') {
        artifactory.publishBuildInfo buildInfo
    }

    stage ('Build image') {
        rtMaven.run pom: 'pom.xml', goals: 'dockerfile:build'
    }

}