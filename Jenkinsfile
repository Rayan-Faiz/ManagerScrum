pipeline {
    agent any
    tools{
        jdk 'jdk'
        maven 'mvn'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
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
                sh 'mvn checkstyle:checkstyle'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker buildx bake'
                sh 'docker-compose up'
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
