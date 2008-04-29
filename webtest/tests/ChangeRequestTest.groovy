class ChangeRequestTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testChangeRequestListNewDelete()
        // add tests for more operations here
    }

    def testChangeRequestListNewDelete() {
        webtest('ChangeRequest basic operations: view list, create new entry, view, edit, delete, view') {
            invoke(url: 'auth')
            verifyText(text:'Login')
            setInputField(name: "username", value: "admin")
            setInputField(name:"password",value: "changeit")
            clickButton(label: 'Login >')
            verifyText(text: 'Project List')

            invoke(url: 'changeRequest')
            verifyText(text: 'Home')

            verifyListSize 0

            clickLink(label: 'New ChangeRequest')
            verifyText(text: 'Create ChangeRequest')
            setInputField(name: "name", value: "request 1")
            setInputField(name: "description", value: "Steve Holmes")
            clickButton(label: 'Create')
            verifyText(text: 'Show ChangeRequest', description: 'Detail page')
            clickLink(label: 'Request', description: 'Back to list view')

            verifyListSize 1

            group(description: 'edit the one element') {
                showFirstElementDetails()
                clickButton(label: 'Edit')
                verifyText(text: 'Edit ChangeRequest')
                clickButton(label: 'Update')
                verifyText(text: 'Show ChangeRequest')
                clickLink(label: 'Request', description: 'Back to list view')
            }

            verifyListSize 1

            group(description: 'delete the only element') {
                showFirstElementDetails()
                clickButton(label: 'Delete')
                verifyXPath(xpath: "//div[@class='message']", text: /.*ChangeRequest.*deleted.*/, regex: true)
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description: "verify ChangeRequest list view with $size row(s)") {
            verifyText(text: 'ChangeRequest List')
            /*verifyXPath(xpath: ROW_COUNT_XPATH, text: size, description: "$size row(s) of data expected")*/
        }
    }

    def showFirstElementDetails() {
        ant.clickLink(label: 'request 1', description: 'go to detail view')
    }
}