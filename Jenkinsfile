pipeline {
    agent any
    tools{
        jdk 'jdk'
        maven 'mvn'
    }
    stages {
        stage('Preparation') {
            steps {
                script {
                    sh 'systemctl start docker'
                }
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
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
