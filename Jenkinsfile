pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'http://sonarqube:9000'  // Your SonarQube server URL
        SONAR_TOKEN = credentials('sonar')  // The token ID you saved in Jenkins credentials
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building project...'
                sh './mvnw clean install -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                sh './mvnw sonar:sonar -Dsonar.login=$SONAR_TOKEN'
            }
        }

        stage('Unit Tests') {
            steps {
                echo 'Running unit tests...'
                sh './mvnw test'
            }
        }
    }
}
