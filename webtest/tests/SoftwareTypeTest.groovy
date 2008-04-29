class SoftwareTypeTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testSoftwareTypeListNewDelete()
        // add tests for more operations here
    }

    def testSoftwareTypeListNewDelete() {
        webtest('SoftwareType basic operations: view list, create new entry, view, edit, delete, view') {
            invoke      (url: 'softwareType')
            verifyText  (text:'Home')

            verifyListSize 0

            clickLink   (label:'New SoftwareType')
            verifyText  (text: 'Create SoftwareType')
            setInputField(label: "Description:", value: "whatever")
            setInputField(label: "Order:", value: "1")
            clickButton (label:'Create')
            verifyText  (text: 'Show SoftwareType', description:'Detail page')
            clickLink   (label:'Software', description:'Back to list view')

            verifyListSize 1

            group(description:'edit the one element') {
                showFirstElementDetails()
                clickButton (label:'Edit')
                verifyText  (text: 'Edit SoftwareType')
                clickButton (label:'Update')
                verifyText  (text: 'Show SoftwareType')
                clickLink   (label:'Software', description:'Back to list view')
            }

            verifyListSize 1

            group(description:'delete the only element') {
                showFirstElementDetails()
                clickButton (label:'Delete')
                verifyXPath (xpath:"//div[@class='message']", text:/.*SoftwareType.*deleted.*/, regex:true)
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description:"verify SoftwareType list view with $size row(s)") {
            verifyText  (text:'SoftwareType List')
            /*verifyXPath (xpath:ROW_COUNT_XPATH, text:size, description:"$size row(s) of data expected")*/
        }
    }

    def showFirstElementDetails() {
        ant.clickLink(label:'whatever', description:'go to detail view')
    }
}