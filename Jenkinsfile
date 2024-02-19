pipeline {
    agent any
    
    tools {
        // Define Maven tool
        maven 'MAVEN'
    }
    
    stages {
        stage('Checkout Code') { 
            git 'https://github.com/maping/java-maven-calculator-web-app.git'
        }
        
        stage('JUnit Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        
        stage('Integration Test') {
            steps {
                sh 'mvn integration-test'
            }
        }
        
        stage('Performance Test') {
            steps {
                sh 'mvn verify'
            }
        }
                
        stage('Build') {
            steps {
                // Clean and package the project using Maven
                sh 'mvn clean package'
            }
            post {
                success {
                    // Archive the generated WAR file
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }
        
        stage('Deploy to Tomcat server') {
            steps {
                // Deploy the WAR file to Tomcat server
                deploy adapters: [tomcat9(credentialsId: '4fd75e70-72d4-4eca-88f2-ceb50d550227', path: '', url: 'http://74.235.239.120/')], contextPath: null, war: '**/*.war'
            }
        }
    }
}
