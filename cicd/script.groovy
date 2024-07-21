def buildApp() {
    echo "Building.. in Branch: ${BRANCH_NAME}"
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