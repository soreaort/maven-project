pipeline {
	agent any
	parameters {
		string(name: 'tomcat_stg', defaultValue: '/opt/tomcat9-staging/webapps', description: 'Staging Instance')
		string(name: 'tomcat_prod', defaultValue: '/opt/tomcat9-production/webapps', description: 'Production Instance')
	}
	triggers {
		pollSCM('* * * * *')
	 }
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
		stage ('Deployments'){
			stage ('Deploy to Staging'){
				steps {
					sh "cp **/target/*.war ${params.tomcat_stg}"
				}
			}
			stage ("Deploy to Production"){
				steps{
					sh "cp **/target/*.war ${params.tomcat_prod}"
			    }
			}
		}
	}
}
