/*def SCAN_REPOSITORY=""
def SMART_CHECK_SERVER=""
def AWS_REGION=""
def GIT_REPO=""
def GIT_CREDENTIALS=""
def DSSC_CREDENTIALS=""
def REPO_CREDENTIALS=""
def SCAN_REGISTRY=""
def BRANCH_NAME = "master"
import groovy.json.JsonOutput */
node {
 
    cleanWs()
    stage "Checkout Code"
    //sh "echo $SCAN_REPOSITORY"
    sh "git clone https://github.com/mkhaled93/test.git/"
    //sh "printenv"
    
    //checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: BRANCH_NAME]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: '.']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: GIT_CREDENTIALS, url: GIT_REPO]]]
    stage "Scan"
    //sh "uname -r"
    //sh "sudo yum install https://centos7.iuscommunity.org/ius-release.rpm"
    //sh "sudo yum update"
    sh "sudo yum install -y python36"
    sh "sudo python3 --version"
    sh "sudo curl -O https://bootstrap.pypa.io/get-pip.py"
    sh "sudo python3 get-pip.py --user"
    sh "sudo curl -O https://bootstrap.pypa.io/get-pip.py"
    sh "sudo python3 get-pip.py"
    //sh "sudo su"
    sh "pip3 install -r https://raw.githubusercontent.com/OzNetNerd/Cloud-Conformity-Pipeline-Scanner/master/code/requirements.txt --user"
    sh "sudo wget https://raw.githubusercontent.com/OzNetNerd/Cloud-Conformity-Pipeline-Scanner/master/code/scanner.py"
    sh "sudo python3 scanner.py"

    stage "Build the Environment"
    sh "aws --region us-west-2 cloudformation create-stack --stack-name myteststack --template-body file://test/s3.yml --capabilities CAPABILITY_NAMED_IAM"
    /*sh "docker build -t $SCAN_REPOSITORY ."
    sh "printenv"
    stage "Send to Repository"
    sh "eval \$(aws ecr get-login --no-include-email --region $AWS_REGION | sed 's|https://||')"
    sh "docker tag $SCAN_REPOSITORY:latest $SCAN_REGISTRY/$SCAN_REPOSITORY:$BUILD_ID"
    sh "docker push $SCAN_REGISTRY/$SCAN_REPOSITORY:$BUILD_ID"
 
 

    /*stage "Smart Check"

     def SCAN_IMAGE="$SCAN_REGISTRY/$SCAN_REPOSITORY:$BUILD_ID"
     sh "echo $SCAN_IMAGE"


    withCredentials([
        usernamePassword([
            credentialsId: DSSC_CREDENTIALS,
            usernameVariable: "DSSC_USER",
            passwordVariable: "DSSC_PASSWORD",
        ])
    ]){
        withCredentials([
            usernamePassword([
                credentialsId: REPO_CREDENTIALS,
            usernameVariable: "Access_ID",
            passwordVariable: "Secret_ID",
            ])
        ]){
            smartcheckScan([
                imageName: SCAN_IMAGE,
                smartcheckHost: SMART_CHECK_SERVER,
                smartcheckUser: DSSC_USER,
                smartcheckPassword: DSSC_PASSWORD,
                insecureSkipTLSVerify: true,
                findingsThreshold: JsonOutput.toJson([
                    "malware": 1,
                    "vulnerabilities": [
                        "critical": 1,
                        "high": 1,
                    ],
                    "contents": [
                        "critical": 1,
                        "high": 1,
                    ]
                ]).toString(),
                imagePullAuth: JsonOutput.toJson([
                    "aws": [
						"region": "ap-southeast-1",
						"accessKeyID": Access_ID,
						"secretAccessKey": Secret_ID,
						]
                ]).toString(),
            ])
        }
    }
    sh "cat "
    stage "Certify Release"
    sh "docker tag $SCAN_REPOSITORY:latest $SCAN_REGISTRY/$SCAN_REPOSITORY:$BRANCH_NAME"
    stage "Deploy to Production"

    sh "docker push $SCAN_REGISTRY/$SCAN_REPOSITORY:$BRANCH_NAME" */
}