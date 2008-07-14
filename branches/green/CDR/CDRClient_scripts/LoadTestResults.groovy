import com.delegata.cdr.client.util.*
import com.delegata.cdr.client.domain.*

/**
 * User: sholmes
 * Date: Jul 9, 2008
 * Time: 9:57:29 AM
 */
def root = "http://solutions.delegata.com:8085/CDR"

def username = "admin"
def password = "changeit"

def projectName = "APS"
def environmentName = "Staging"


def buildCiName = "build"
def buildCiCategory = "software"
def buildCiType = "Application"
def buildCiStatusName = "Active"

def testResultCiName = "User Acceptance Test Results"
def testResultCiAuthor = "Yvonne Wilson"
def testResultCiType = "Acceptance"
def testResultFileAddress = "/usr/local/hudson_home/jobs/green_integration/workspace/CDR/CDR/CDRClient_scripts/Test_Results.pdf"
def testResultStatus = "Active"

def relationType = "tests"

CDRClient client = new CDRClient(root:root)
client.authenticate(username,password)
client.getConfigurationItem(new Software(name:buildCiName, category:buildCiCategory),environmentName,projectName,buildCiStatusName, buildCiType)
println client.body
def build = new XmlSlurper().parseText(client.body)
client.startServiceOrder()
client.createConfigurationItemDocumentType(new TestResult(testResultType: new TestResultType(description:testResultCiType), name:testResultCiName,author:testResultCiAuthor,document: new File(testResultFileAddress)))
println client.body
client.createRelation(new Relation(thatCI: new Software(id:"${build.@id}"), reference: new RelationReference(name:relationType, description:relationType)),0)
println client.body
client.createStatus(new Status( reference: new StatusReference(name:testResultStatus, description:testResultStatus)),0)
client.persistServiceOrder()
