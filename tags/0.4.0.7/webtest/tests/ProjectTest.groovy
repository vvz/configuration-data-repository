class ProjectTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testProjectListNewDelete()
        // add tests for more operations here
    }

    def testProjectListNewDelete() {
        webtest('Project basic operations: view list, create new entry, view, edit, delete, view') {
            invoke(url: 'auth')
            verifyText(text:'Login')
            setInputField(name: "username", value: "admin")
            setInputField(name:"password",value: "changeit")
            clickButton(label: 'Login >')
            verifyText(text: 'Project List')

            invoke(url: 'project')
            verifyText(text: 'Home')

            verifyListSize 0

            clickLink(label: 'New Project')
            verifyText(text: 'Create Project')
            setInputField(name: "name", value: "APS")
            setInputField(name: "description", value: "APS Project")
            setInputField(name: "ownerName", value: "Steve Holmes")
            setInputField(name: "ownerEmail", value: "sholmes@delegata.com")
            clickButton(label: "Create")
            verifyText(text: "Show Project", description: "Verify that text is contained in the page")
            clickLink(label: "Project", description: 'Back to List view')

            verifyListSize 1

            group(description: 'edit the one element') {
                showFirstElementDetails()
                clickButton(label: 'Edit')
                verifyText(text: 'Edit Project')
                clickButton(label: 'Update')
                verifyText(text: 'Show Project')
                clickLink(label: "Project", description: 'Back to List view')
            }

            verifyListSize 1

            group(description: 'delete the only element') {
                showFirstElementDetails()
                clickButton(label: 'Delete')
                verifyXPath(xpath: "//div[@class='message']", text: /.*Project.*deleted.*/, regex: true)
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description: "verify Project list view with $size row(s)") {
            verifyText(text: 'Project List')
            /*verifyXPath(xpath: ROW_COUNT_XPATH, text: size, description: "$size row(s) of data expected")*/
        }
    }

    def showFirstElementDetails() {
        ant.clickLink(label: 'APS', description: 'go to detail view')
    }
}