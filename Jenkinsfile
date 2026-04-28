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
        stage('Test') {
            steps {
                echo 'Hello World'
            }
        }
    }
}
