pipeline {
    agent any
    
    tools {
        maven 'MAVEN'
    }
    
    stages {
        stage("Build") {
            steps {
                // Build the project
                sh 'mvn clean package'
            }
            post {
                success {
                    echo "Archiving the Artifacts"
                    // Archive the generated WAR file
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }
        
        stage('Run Tests') {
            steps {
                // Run unit tests
                sh 'mvn test'
                // Optionally, you can run additional tests here
            }
            post {
                success {
                    echo "Tests Passed Successfully"
                }
                failure {
                    echo "Tests Failed. Please check the test results."
                }
            }
        }
    }
}
