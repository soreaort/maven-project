pipeline {
    agent any
    stages{
        stage('Build'){
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                    echo 'Now Archiving...'
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }
        stage ('Staging and Analysis'){
            parallel{
                stage ('Deploy to Staging'){
                    steps {
                        build job: 'deploy-to-staging'
                    }
                }
                stage ('Analysis'){
                    steps {
                        build job: 'static-analysis'
                    }
                }
            }
        }
        stage ('Deploy to Production'){
            steps{
                timeout(time:5, unit:'DAYS'){
                    input message:'Approve PRODUCTION Deployment?'
                    //input message:'Approve PRODUCTION Deployment?',submitter: XXX (Tell Jenkins who is the approver)
                }
                build job: 'deploy-to-production'
            }
            post {
                success {
                    echo 'Code deployed to Production.'
                }
                failure {
                    echo ' Deployment failed.'
                }
            }
        }
    }
}
