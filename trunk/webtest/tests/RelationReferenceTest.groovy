class RelationReferenceTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testRelationReferenceListNewDelete()
        // add tests for more operations here
    }

    def testRelationReferenceListNewDelete() {
        webtest('RelationReference basic operations: view list, create new entry, view, edit, delete, view') {
            invoke(url: 'relationReference')
            verifyText(text: 'Home')

            verifyListSize 0

            clickLink(label: 'New RelationReference')
            verifyText(text: 'Create RelationReference')
            setInputField(forLabel: "Name:", value: "thisReference")
            setInputField(forLabel: "Description:", value: "this")
            setInputField(forLabel: "Order:", value: "1")
            clickButton(label: 'Create')
            verifyText(text: 'Show RelationReference', description: 'Detail page')
            clickLink(label: 'Relation', description: 'Back to list view')

            verifyListSize 1

            group(description: 'edit the one element') {
                showFirstElementDetails()
                clickButton(label: 'Edit')
                verifyText(text: 'Edit RelationReference')
                clickButton(label: 'Update')
                verifyText(text: 'Show RelationReference')
                clickLink(label: 'Relation', description: 'Back to list view')
            }

            verifyListSize 1

            group(description: 'delete the only element') {
                showFirstElementDetails()
                clickButton(label: 'Delete')
                verifyXPath(xpath: "//div[@class='message']", text: /.*RelationReference.*deleted.*/, regex: true)
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description: "verify RelationReference list view with $size row(s)") {
            verifyText(text: 'RelationReference List')
            /*verifyXPath(xpath: ROW_COUNT_XPATH, text: size, description: "$size row(s) of data expected")*/
        }
    }

    def showFirstElementDetails() {
        ant.clickLink(label: 'thisReference', description: 'go to detail view')
    }
}