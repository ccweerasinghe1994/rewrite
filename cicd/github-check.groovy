import groovy.json.JsonOutput

def readCsvFile() {
    // Assuming the file path is known and exists
    return readFile('target/path/to/org.openrewrite.java.dependencies.table.VulnerabilityReport.csv')
}

def createCheckRun(String csvData) {
    def payload = JsonOutput.toJson([
            name      : 'Vulnerability Report',
            head_sha  : 'YOUR_COMMIT_SHA_HERE', // This needs to be dynamically obtained or passed in
            status    : 'completed',
            conclusion: 'neutral',
            output    : [
                    title  : 'Dependency Vulnerability Report',
                    summary: 'Summary of the report',
                    text   : csvData // You might need to format csvData for better display
            ]
    ])

    def token = 'YOUR_GITHUB_TOKEN'
    def repo = 'YOUR_REPO'
    def owner = 'YOUR_OWNER'

    sh """
        curl -X POST -H "Authorization: token ${token}" \
        -H "Accept: application/vnd.github.v3+json" \
        https://api.github.com/repos/${owner}/${repo}/check-runs \
        -d '${payload}'
    """
}

def csvData = readCsvFile()
createCheckRun(csvData as String)