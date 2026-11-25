pipeline {
    agent any

    stages {
        stage('Run Regression Tests') {
            steps {
                sh 'mvn spotless:apply'
            }
        }
        stage('Run Regression Tests') {
            steps {
                sh 'mvn clean'
            }
        }
    }
}
