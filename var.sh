    CC_API_KEY="25MpkR3skTLvjw7zPRnP3AiWNBvnwva5aoMwLPt3ZF8VX2gNE7zS3YGox8UfXGz2"
    CC_REGION="ap-southeast-2"
    CFN_TEMPLATE_FILE_LOCATION="https://github.com/mkhaled93/test/blob/master/s3.yml"
    echo $CFN_TEMPLATE_FILE_LOCATION
    printenv
    python3 scanner.py