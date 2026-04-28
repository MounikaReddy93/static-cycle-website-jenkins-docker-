pipeline {
    agent any
    tools {
        maven 'maven'
    }
    environment {
        IMAGE_NAME = "jenkins-cicd-demo"
        DOCKER_REPO = "mouni668645/jenkins-cicd-demo"
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
        stage('Docker Image') {
            steps {
                sh '''
                docker build -t ${IMAGE_NAME}:latest .
                '''
            }
        }
        stage('Docker Push (Docker Hub)') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    docker tag ${IMAGE_NAME}:latest ${DOCKER_REPO}:latest
                    docker push ${DOCKER_REPO}:latest
                    '''
                }
            }
        }
    }  
    post {
        success {
            echo "✅ Pipeline completed successfully"
        }
        failure {
            echo "❌ Pipeline failed"
        }
    }
}
