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

        stage('Deploy') {
            steps {
                dir('backend/claimwise-ai') {

                    // Kill any running Java app (avoid port conflict)
                    bat 'taskkill /F /IM java.exe || exit 0'

                    // Run specific jar (replace with your actual jar name)
                    bat 'start /B java -jar target\\claimwise-ai-0.0.1-SNAPSHOT.jar'
                }
            }
        }
    }
}