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
        
        stage('Deploy to Tomcat Server') {
            steps {
                // Deploy the WAR file to Tomcat server
                deploy adapters: [tomcat9(credentialsId: '4fd75e70-72d4-4eca-88f2-ceb50d550227', path: '', url: 'http://74.235.239.120/')], contextPath: '/java-webapp', war: '**/*.war'
            }
        }
    }
}
