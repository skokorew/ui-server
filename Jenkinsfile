node {
    def artifactory = Artifactory.server "local_artifactory"
    def rtMaven = Artifactory.newMavenBuild()
    def buildInfo

    stage ('Configure') {
        rtMaven.tool = 'local_mvn'
        rtMaven.deployer releaseRepo: 'ext-release-local', snapshotRepo: 'ext-snapshot-local', server: artifactory
        rtMaven.resolver releaseRepo: 'maven-central', snapshotRepo: 'maven-central', server: artifactory
        buildInfo = Artifactory.newBuildInfo()
    }

    stage ('Build') {
        rtMaven.run pom: 'pom.xml', goals: 'clean install', buildInfo: buildInfo
    }

    stage ('Publish build info') {
        artifactory.publishBuildInfo buildInfo
    }

}