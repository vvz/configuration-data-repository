class DocumentationTypeTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testDocumentationTypeListNewDelete()
        // add tests for more operations here
    }

    def testDocumentationTypeListNewDelete() {
        webtest('DocumentationType basic operations: view list, create new entry, view, edit, delete, view') {
            invoke      (url: 'documentationType')
            verifyText  (text:'Home')

            verifyListSize 0

            clickLink   (label:'New DocumentationType')
            verifyText  (text: 'Create DocumentationType')
            setInputField(forLabel: "Description:", value: "whatever")
            setInputField(forLabel: "Order:", value: "1")
            clickButton (label:'Create')
            verifyText  (text: 'Show DocumentationType', description:'Detail page')
            clickLink   (label:'Documentation', description:'Back to list view')

            verifyListSize 1

            group(description:'edit the one element') {
                showFirstElementDetails()
                clickButton (label:'Edit')
                verifyText  (text: 'Edit DocumentationType')
                clickButton (label:'Update')
                verifyText  (text: 'Show DocumentationType')
                clickLink   (label:'Documentation', description:'Back to list view')
            }

            verifyListSize 1

            group(description:'delete the only element') {
                showFirstElementDetails()
                clickButton (label:'Delete')
                verifyXPath (xpath:"//div[@class='message']", text:/.*DocumentationType.*deleted.*/, regex:true)
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description:"verify DocumentationType list view with $size row(s)") {
            verifyText  (text:'DocumentationType List')
            /*verifyXPath (xpath:ROW_COUNT_XPATH, text:size, description:"$size row(s) of data expected")*/
        }
    }

    def showFirstElementDetails() {
        ant.clickLink(label:'whatever', description:'go to detail view')
    }
}