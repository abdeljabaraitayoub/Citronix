pipeline {
    agent any


    triggers {
        githubPush()
    }

    environment {
        SONARQUBE_URL = 'http://sonarqube:9000'
        SONAR_TOKEN = credentials('sonar')
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_IMAGE_NAME = 'my-app'
        DOCKER_TAG = 'latest'
        DOCKER_CREDENTIALS = credentials('docker-hub')
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
    }

    post {
        success {
            mail to: 'abdeljabarayoubi@gmail.com',
                 subject: "Jenkins Build Success: ${env.JOB_NAME} ${env.BUILD_NUMBER}",
                 body: "The build was successful! You can check it at ${env.BUILD_URL}"
        }
        failure {
            echo 'Build failed!'
        }
    }
}
