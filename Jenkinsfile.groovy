node {
 
    cleanWs()
    stage "Checkout Code"

    sh "git clone https://github.com/mkhaled93/test.git/"

    stage "Scan"

    sh "sudo yum install -y python36"
    sh "sudo curl -O https://bootstrap.pypa.io/get-pip.py"
    sh "sudo python3 get-pip.py --user"
    sh "pip3 install -r https://raw.githubusercontent.com/OzNetNerd/Cloud-Conformity-Pipeline-Scanner/master/code/requirements.txt --user"
    sh "wget https://raw.githubusercontent.com/OzNetNerd/Cloud-Conformity-Pipeline-Scanner/master/code/scanner.py"
    sh "python3 scanner.py"
    artifacts "findings.json"

    stage "Build the Environment"

    sh "aws --region us-west-2 cloudformation create-stack --stack-name myteststack --template-body file://$CFN_TEMPLATE_FILE_LOCATION --capabilities CAPABILITY_NAMED_IAM"
}