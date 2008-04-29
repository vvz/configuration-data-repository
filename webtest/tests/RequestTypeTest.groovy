class RequestTypeTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testRequestTypeListNewDelete()
        // add tests for more operations here
    }

    def testRequestTypeListNewDelete() {
        webtest('RequestType basic operations: view list, create new entry, view, edit, delete, view') {
            invoke      (url: 'requestType')
            verifyText  (text:'Home')

            verifyListSize 0

            clickLink   (label:'New RequestType')
            verifyText  (text: 'Create RequestType')
            setInputField(label: "Description:", value: "whatever")
            setInputField(label: "Order:", value: "1")
            clickButton (label:'Create')
            verifyText  (text: 'Show RequestType', description:'Detail page')
            clickLink   (label:'Request', description:'Back to list view')

            verifyListSize 1

            group(description:'edit the one element') {
                showFirstElementDetails()
                clickButton (label:'Edit')
                verifyText  (text: 'Edit RequestType')
                clickButton (label:'Update')
                verifyText  (text: 'Show RequestType')
                clickLink   (label:'Request', description:'Back to list view')
            }

            verifyListSize 1

            group(description:'delete the only element') {
                showFirstElementDetails()
                clickButton (label:'Delete')
                /*verifyXPath (xpath:"//div[@class='message']", text:/.*RequestType.*deleted.*//*, regex:true)*/
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description:"verify RequestType list view with $size row(s)") {
            verifyText  (text:'RequestType List')
            /*verifyXPath (xpath:ROW_COUNT_XPATH, text:size, description:"$size row(s) of data expected")*/
        }
    }

    def showFirstElementDetails() {
        ant.clickLink(label:'whatever', description:'go to detail view')
    }
}