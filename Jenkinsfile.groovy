node {
 
    cleanWs()
    stage "Checkout Code"

    sh "git clone https://github.com/mkhaled93/test.git/"

    stage "Scan"

    sh "pip3 install -r https://raw.githubusercontent.com/OzNetNerd/Cloud-Conformity-Pipeline-Scanner/master/code/requirements.txt --user"
    sh "wget https://raw.githubusercontent.com/OzNetNerd/Cloud-Conformity-Pipeline-Scanner/master/code/scanner.py"
    sh "python3 scanner.py"

    stage "Build the Environment"

    sh "aws --region us-west-2 cloudformation create-stack --stack-name myteststackk --template-body file://$CFN_TEMPLATE_FILE_LOCATION --capabilities CAPABILITY_NAMED_IAM"
}

