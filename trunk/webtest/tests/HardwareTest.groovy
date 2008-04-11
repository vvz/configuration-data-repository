class HardwareTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testHardwareListNewDelete()
        // add tests for more operations here
    }

    def testHardwareListNewDelete() {
        webtest('Hardware basic operations: view list, create new entry, view, edit, delete, view') {
            invoke(url: 'hardware')
            verifyText(text: 'Home')

            verifyListSize 0

            /*clickLink(label: 'New Hardware')
            verifyText(text: 'Create Hardware')
            clickButton(label: 'Create')
            verifyText(text: 'Show Hardware', description: 'Detail page')*/
            clickLink(label: "Hardware")
            clickLink(label: "New Hardware")
            setInputField(forLabel: "Name:", value: "Solutions")
            setInputField(name: "description", value: "Solutions Server")
            setInputField(forLabel: "Author:", value: "Steve Holmes")
            setInputField(forLabel: "Owner Name:", value: "Steve Holmes")
            setInputField(forLabel: "Owner Email:", value: "sholmes@delegata.com")
            clickButton(label: "Create")
            verifyText(description: "Verify that text is contained in the page", text: "Create Hardware")
            clickLink(label: "Hardware")
            /*clickLink(label: 'List', description: 'Back to list view')*/

            verifyListSize 1

            group(description: 'edit the one element') {
                showFirstElementDetails()
                /*clickLink(label: "Solutions")*/
                clickButton(label: 'Edit')
                verifyText(text: 'Edit Hardware')
                clickButton(label: 'Update')
                verifyText(text: 'Show Hardware')
                /*clickLink(label: 'List', description: 'Back to list view')*/
                clickLink(label: "Hardware")
            }

            verifyListSize 1

            //Create Relation Reference
            /*clickLink(label: "Solutions")*/
            showFirstElementDetails()
            invoke(url: "http://localhost:8080/CDR/relationReference/list")
            clickLink(label: "New RelationReference")
            setInputField(forLabel: "Name:", value: "exists in")
            setInputField(forLabel: "Description:", value: "exists in")
            setInputField(forLabel: "Order:", value: "1")
            clickButton(label: "Create")
            verifyText(description: "Verify that text is contained in the page", text: "Show RelationReference")

            //Test Create Relation
            clickLink(label:'Home')
            clickLink(label: "Hardware")
            /*clickLink(label: "Solutions")*/
            showFirstElementDetails()
            clickLink(label: "New Relation")                                                
            clickButton(label: "Create")
            verifyText(description: "Verify that text is contained in the page", text: "Show Relation")

            //Test Edit Relation
            clickLink(label:'Home')
            clickLink(label: "Hardware")
            /*clickLink(label: "Solutions")*/
            showFirstElementDetails()
            clickLink(label: "Solutions depends on Solutions")
            clickButton(label: "Edit")
            verifyText(description: "Verify that text is contained in the page", text: "Edit Relation")
            clickButton(label: "Update")
            verifyText(description: "Verify that text is contained in the page", text: "updated")

            group(description: 'delete the only element') {
                showFirstElementDetails()
                clickButton(label: 'Delete')
                verifyXPath(xpath: "//div[@class='message']", text: /.*Hardware.*deleted.*/, regex: true)
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description: "verify Hardware list view with $size row(s)") {
            verifyText(text: 'Hardware List')
            /*verifyXPath(xpath: ROW_COUNT_XPATH, text: size, description: "$size row(s) of data expected")*/
        }
    }

    def showFirstElementDetails() {
        ant.clickLink(label: 'Solutions', description: 'go to detail view')
    }
}