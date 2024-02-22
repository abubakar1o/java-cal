pipeline {
    agent any
    
    stages {
        stage('Deploy to Tomcat Server') {
            steps {
                // Deploy the WAR file to Tomcat server
                deploy adapters: [tomcat9(credentialsId: '4fd75e70-72d4-4eca-88f2-ceb50d550227', path: '', url: 'http://74.235.239.120/')], contextPath: '/java-webapp', war: '**/*.war'
            }
        }
    }
}
