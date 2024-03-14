def Buildjar()
    echo "Building application into .jar..."
    sh "mvn clean package"

def Buildimage() {
    echo "Packaging application into Dockerfile..."
}

def Deployimage() {
    withCredentials([usernamePassword(cerdentialId:'DockerHub-secret', passwordVarriable: 'PASS', usernameVarriable: 'USER')]){
        sh 'docker buils -t repo_name:${BUILD_ID} .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker pushrepo_name:${BUILD_ID}'
    }
}

def Deploystaging() {
        echo "Deploying the Application..."
}
return this