class TestResultTypeTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testTestResultTypeListNewDelete()
        // add tests for more operations here
    }

    def testTestResultTypeListNewDelete() {
        webtest('TestResultType basic operations: view list, create new entry, view, edit, delete, view') {
            invoke      (url: 'testResultType')
            verifyText  (text:'Home')

            verifyListSize 0

            clickLink   (label:'New TestResultType')
            verifyText  (text: 'Create TestResultType')
            setInputField(forLabel: "Description:", value: "whatever")
            setInputField(forLabel: "Order:", value: "1")
            clickButton (label:'Create')
            verifyText  (text: 'Show TestResultType', description:'Detail page')
            clickLink   (label:'Test Result', description:'Back to list view')

            verifyListSize 1

            group(description:'edit the one element') {
                showFirstElementDetails()
                clickButton (label:'Edit')
                verifyText  (text: 'Edit TestResultType')
                clickButton (label:'Update')
                verifyText  (text: 'Show TestResultType')
                clickLink   (label:'Test Result', description:'Back to list view')
            }

            verifyListSize 1

            group(description:'delete the only element') {
                showFirstElementDetails()
                clickButton (label:'Delete')
                /*verifyXPath (xpath:"//div[@class='message']", text:/.*TestResultType.*deleted.*//*, regex:true)*/
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description:"verify TestResultType list view with $size row(s)") {
            verifyText  (text:'TestResultType List')
            /*verifyXPath (xpath:ROW_COUNT_XPATH, text:size, description:"$size row(s) of data expected")*/
        }
    }

    def showFirstElementDetails() {
        ant.clickLink(label:'whatever', description:'go to detail view')
    }
}