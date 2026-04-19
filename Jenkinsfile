pipeline {
    agent any

    tools {
        maven 'Maven-3'
    }

    stages {

        stage('Build') {
            steps {
                dir('backend/claimwise-ai') {
                    bat 'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
                dir('backend/claimwise-ai') {
                    bat 'mvn test'
                }
            }
        }

        stage('Package') {
            steps {
                dir('backend/claimwise-ai') {
                    bat 'dir target'
                }
            }
        }
    }
}