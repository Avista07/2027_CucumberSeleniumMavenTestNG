pipeline {
    agent any

    stages {
        stage('Run spotless:apply') {
            steps {
                sh 'mvn spotless:apply'
            }
        }
        stage('Run mvn clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Run mvn test') {
            steps {
                sh 'mvn test -Ptest'
            }
        }
    }
}
