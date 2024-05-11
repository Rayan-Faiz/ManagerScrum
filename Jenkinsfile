pipeline {
    agent any
    
    environment {
        // Define the Maven installation name
        MAVEN_HOME = tool 'Maven'
    }

    stages {
        stage('Build') {
            steps {
                // Use the installed Maven version to execute Maven commands
                sh "${MAVEN_HOME}/bin/mvn clean package"
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
