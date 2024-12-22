pipeline {
    agent any

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

        stage('Unit Tests') {
            steps {
                echo 'Running unit tests...'
                sh './mvnw test'
            }
        }
    }
}
