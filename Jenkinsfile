pipeline {
    agent any
    tools {
        maven 'maven'
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
                echo 'This runs ONLY if Quality Gate PASSES'
            }
        }
    }
}

