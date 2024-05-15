pipeline {
    agent any
    tools{
        jdk 'jdk'
        maven 'mvn'
    }
    stages {
        stage('Build') {
            steps {
                //withDockerRegistry(credentialsId: 'a0029d18-f019-497c-b9d5-f305b66caab3', toolName: 'docker') {}
                //sh 'mvn clean package'
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
                    //withDockerRegistry(credentialsId: 'a0029d18-f019-497c-b9d5-f305b66caab3', toolName: 'docker') {}
                    sh 'docker buildx bake'
                    sh 'docker-compose up'
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
