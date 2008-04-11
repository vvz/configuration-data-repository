class HardwareTypeTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testHardwareTypeListNewDelete()
        // add tests for more operations here
    }

    def testHardwareTypeListNewDelete() {
        webtest('HardwareType basic operations: view list, create new entry, view, edit, delete, view') {
            invoke      (url: 'hardwareType')
            verifyText  (text:'Home')

            verifyListSize 0

            clickLink   (label:'New HardwareType')
            verifyText  (text: 'Create HardwareType')
            setInputField(forLabel: "Description:", value: "whatever")
            setInputField(forLabel: "Order:", value: "1")
            clickButton (label:'Create')
            verifyText  (text: 'Show HardwareType', description:'Detail page')
            clickLink   (label:'Hardware', description:'Back to list view')

            verifyListSize 1

            group(description:'edit the one element') {
                showFirstElementDetails()
                clickButton (label:'Edit')
                verifyText  (text: 'Edit HardwareType')
                clickButton (label:'Update')
                verifyText  (text: 'Show HardwareType')
                clickLink   (label:'Hardware', description:'Back to list view')
            }

            verifyListSize 1

            group(description:'delete the only element') {
                showFirstElementDetails()
                clickButton (label:'Delete')
                verifyXPath (xpath:"//div[@class='message']", text:/.*HardwareType.*deleted.*/, regex:true)
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description:"verify HardwareType list view with $size row(s)") {
            verifyText  (text:'HardwareType List')
            /*verifyXPath (xpath:ROW_COUNT_XPATH, text:size, description:"$size row(s) of data expected")*/
        }
    }

    def showFirstElementDetails() {
        ant.clickLink(label:'whatever', description:'go to detail view')
    }
}