class NetworkTypeTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testNetworkTypeListNewDelete()
        // add tests for more operations here
    }

    def testNetworkTypeListNewDelete() {
        webtest('NetworkType basic operations: view list, create new entry, view, edit, delete, view') {
            invoke      (url: 'networkType')
            verifyText  (text:'Home')

            verifyListSize 0

            clickLink   (label:'New NetworkType')
            verifyText  (text: 'Create NetworkType')
            setInputField(label: "Description:", value: "whatever")
            setInputField(label: "Order:", value: "1")
            clickButton (label:'Create')
            verifyText  (text: 'Show NetworkType', description:'Detail page')
            clickLink   (label:'Network', description:'Back to list view')

            verifyListSize 1

            group(description:'edit the one element') {
                showFirstElementDetails()
                clickButton (label:'Edit')
                verifyText  (text: 'Edit NetworkType')
                clickButton (label:'Update')
                verifyText  (text: 'Show NetworkType')
                clickLink   (label:'Network', description:'Back to list view')
            }

            verifyListSize 1

            group(description:'delete the only element') {
                showFirstElementDetails()
                clickButton (label:'Delete')
                /*verifyXPath (xpath:"//div[@class='message']", text:/.*NetworkType.*deleted.*//*, regex:true)*/
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description:"verify NetworkType list view with $size row(s)") {
            verifyText  (text:'NetworkType List')
            /*verifyXPath (xpath:ROW_COUNT_XPATH, text:size, description:"$size row(s) of data expected")*/
        }
    }

    def showFirstElementDetails() {
        ant.clickLink(label:'whatever', description:'go to detail view')
    }
}