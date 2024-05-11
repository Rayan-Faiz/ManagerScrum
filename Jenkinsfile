pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                tool 'Maven'
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                tool 'Maven'
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Code Quality') {
            steps {
                tool 'Maven'
                sh 'mvn checkstyle:checkstyle'
            }
        }
    }

    post {
        success {
            echo 'CI Pipeline completed successfully!'
        }
        failure {
            echo 'CI Pipeline failed! Please check logs and fix any issues.'
        }
    }
}
