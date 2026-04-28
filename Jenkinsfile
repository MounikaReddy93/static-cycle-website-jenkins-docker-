pipeline {
    agent any
    tools {
        maven 'maven'
    }
    environment {
        IMAGE_NAME = "jenkins-cicd-demo"
    }
    stages {
        stage('checkout code') {
            steps {
                git 'https://github.com/MounikaReddy93/static-cycle-website-jenkins-docker-.git'
            }
        }
        stage('Check Maven') {
            steps {
                sh 'mvn -version'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn sonar:sonar'
            }
          }
        }
        
       stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Deploy') {
            steps {
                sh '''
                docker build -t ${IMAGE_NAME}:latest .
                '''
            }
        }
    }
    post {
        success {
            echo "✅ Quality Gate PASSED → Docker image built successfully"
        }
        failure {
            echo "❌ Quality Gate FAILED → Docker build skipped"
        }
    }
}
