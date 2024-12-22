pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'http://sonarqube:9000'
        SONAR_TOKEN = credentials('sonar')
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_IMAGE_NAME = 'my-app'
        DOCKER_TAG = 'latest'
        DOCKER_CREDENTIALS = credentials('docker-hub')  // Add your Docker Hub credentials
    }

    stages {
        stage('docker version') {
            steps {
                echo 'Checking docker version...'
                sh 'docker --version'
            }
        }

        stage('Checkout Code') {
            steps {
                echo 'Checking out code...'
                checkout scm
            }
        }

        stage('Build Project') {
            steps {
                echo 'Building project...'
                sh './mvnw clean install -DskipTests'
            }
        }

        stage('Unit Tests') {
            steps {
                echo 'Running unit tests...'
                sh './mvnw test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                script {
                    sh """
                        ./mvnw clean verify sonar:sonar \\
                        -Dsonar.host.url=${SONARQUBE_URL} \\
                        -Dsonar.login=${SONAR_TOKEN}
                    """
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Docker image...'
                    docker.build("${DOCKER_IMAGE_NAME}:${DOCKER_TAG}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    echo 'Pushing Docker image to Docker Hub...'
                    withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh """
                            echo ${DOCKER_PASSWORD} | docker login -u ${DOCKER_USERNAME} --password-stdin
                            docker push ${DOCKER_REGISTRY}/${DOCKER_USERNAME}/${DOCKER_IMAGE_NAME}:${DOCKER_TAG}
                        """
                    }
                }
            }
        }
    }
}
