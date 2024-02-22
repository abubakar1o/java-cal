pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE_TAG = "java-app"
        DOCKER_CONTAINER_NAME = "java-cal"
        SERVER_IP = "74.235.239.120"
        SERVER_PORT = "80"
    }
    tools {
        maven 'MAVEN'
    }
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the repository
                git branch: 'main', url: 'https://github.com/abubakar1o/java-cal.git'
            }
        }
        
        stage('Build and Test') {
            steps {
                // Build and test the Java application
                sh 'mvn clean package'
            }
            post {
                success {
                    echo "Build and Test succeeded"
                }
                failure {
                    echo "Build and Test failed"
                }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                // Build the Docker image for the Java application
                script {
                    sh "docker build -t java-app ."
                }
            }
            post {
                success {
                    echo "Docker image build succeeded"
                }
                failure {
                    echo "Docker image build failed"
                }
            }
        }
        
        stage('Deploy') {
            steps {
                // Deploy the Docker container on the server
                script {
                    // Stop and remove existing container if it exists
                    sh "ssh abubakar@${SERVER_IP} 'docker stop ${DOCKER_CONTAINER_NAME} || true'"
                    sh "ssh abubakar@${SERVER_IP} 'docker rm ${DOCKER_CONTAINER_NAME} || true'"
                    
                    // Run the Docker container using withDockerContainer block
                    withDockerContainer(args: '-d -p 8000:8080 --name java-cal', image: 'java-app', toolName: 'Docker') {
                        // Add any additional steps or blocks within the Docker container
                        // For example, you can run additional commands or execute scripts inside the container
                        // some block
                    }
                }
            }
            post {
                success {
                    echo "Deployment succeeded"
                }
                failure {
                    echo "Deployment failed"
                }
            }
        }
    }
}
