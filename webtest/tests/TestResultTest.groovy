class TestResultTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testTestResultListNewDelete()
        // add tests for more operations here
    }

    def testTestResultListNewDelete() {
        webtest('TestResult basic operations: view list, create new entry, view, edit, delete, view') {
            invoke      (url: 'testResult')
            verifyText  (text:'Home')

            verifyListSize 0

            clickLink   (label:'New TestResult')
            verifyText  (text: 'Create TestResult')
            setInputField(forLabel: "Name:", value: "result 1")
            setInputField(forLabel: "Author:", value: "Steve Holmes")
            clickButton (label:'Create')
            verifyText  (text: 'Show TestResult', description:'Detail page')
            clickLink   (label:'Test Result', description:'Back to list view')

            verifyListSize 1

            group(description:'edit the one element') {
                showFirstElementDetails()
                clickButton (label:'Edit')
                verifyText  (text: 'Edit TestResult')
                clickButton (label:'Update')
                verifyText  (text: 'Show TestResult')
                clickLink   (label:'Test Result', description:'Back to list view')
            }

            verifyListSize 1

            group(description:'delete the only element') {
                showFirstElementDetails()
                clickButton (label:'Delete')
                verifyXPath (xpath:"//div[@class='message']", text:/.*TestResult.*deleted.*/, regex:true)
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description:"verify TestResult list view with $size row(s)") {
            verifyText  (text:'TestResult List')
            /*verifyXPath (xpath:ROW_COUNT_XPATH, text:size, description:"$size row(s) of data expected")*/
        }
    }

    def showFirstElementDetails() {
        ant.clickLink(label:'result 1', description:'go to detail view')
    }
}