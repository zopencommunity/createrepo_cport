node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/createrepo_cport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/createrepo_cport.git'), string(name: 'PORT_DESCRIPTION', value: 'This program generates a repodata dir and xml files for a repository of rpm packages. This repository is compatible with apt/yum/smart/yast and many other package-repository-related tools.' ), string(name: 'BUILD_LINE', value: 'DEV') ]
  }
}
