def buildApp() {
    echo "Building.. in Branch: ${BRANCH_NAME}"
//    let's build the app here
    sh 'mvn clean package'
}

def testApp() {
    echo 'Testing..'
}

def deployApp() {
    echo 'Deploying....'
    echo "VERSION: ${params.VERSION}"
    withCredentials([usernamePassword(credentialsId: 'server-credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        echo "Deploying to server with username: ${USERNAME} and password: ${PASSWORD}"
    }
}

return this;