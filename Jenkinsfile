pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/harsha2001-sys/TMS.git', branch: 'main'
            }
        }
        stage('Build Spring Boot Application') {
            steps {
                script {
                    dir('C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\Spring Boot Freestyle project') {
                        bat 'mvn clean package'
                    }
                }
            }
        }
        stage('Build Backend Docker Image') {
            steps {
                script {
                    dir('C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\Spring Boot Freestyle project') {
                        bat 'docker buildx build -t tms-backend:latest .'
                    }
                }
            }
        }
        stage('Build Frontend Docker Image') {
            steps {
                script {
                    dir('C:\\Users\\harshavardananb\\Desktop\\TMS') {
                        bat 'docker buildx build -t tms-angular-app:latest .'
                    }
                }
            }
        }
        stage('Deploy with Docker Compose') {
            steps {
                script {
                    dir('C:\\Users\\harshavardananb\\Desktop\\TMS') {
                        bat 'docker-compose up -d'
                    }
                }
            }
        }
    }
}
