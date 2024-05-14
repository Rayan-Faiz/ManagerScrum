pipeline {
    agent any
    tools{
        jdk 'jdk'
        maven 'mvn'
    }
    stages {
        stage('Build') {
            steps {
                withDockerRegistry(credentialsId: 'dee53e40-8179-4798-9fbe-b5f5117ab315', toolName: 'docker') {
                    sh 'mvn clean package'
                }
                
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
                script{
                    withDockerRegistry(credentialsId: 'dee53e40-8179-4798-9fbe-b5f5117ab315', toolName: 'docker') {
                        sh 'docker build -t managerscrum .'
                        sh 'docker-compose up'
                    }
                }
                
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
