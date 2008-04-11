class TestResultTypeController extends JsecAuthBase{
    def scaffold = TestResultType
    static accessControl = {
        // All actions require the 'Observer' role.
        role(name: 'Observer')

        // Alternatively, several actions can be specified.
        role(name: 'Administrator', only: [ 'create', 'edit', 'save', 'update' ])
    }
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max)params.max = 10
        [ testResultTypeList: TestResultType.findAllByType('Test Result') ]
    }

    def show = {
        [ testResultType : TestResultType.get( params.id ) ]
    }

    def delete = {
        def testResultType = TestResultType.get( params.id )
        if(testResultType) {
            testResultType.delete()
            flash.message = "TestResultType ${params.id} deleted."
            redirect(action:list)
        }
        else {
            flash.message = "TestResultType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def testResultType = TestResultType.get( params.id )

        if(!testResultType) {
                flash.message = "TestResultType not found with id ${params.id}"
                redirect(action:list)
        }
        else {
            return [ testResultType : testResultType ]
        }
    }

    def update = {
        def testResultType = TestResultType.get( params.id )
        if(testResultType) {
             testResultType.properties = params
            if(testResultType.save()) {
                flash.message = "TestResultType ${params.id} updated."
                redirect(action:show,id:testResultType.id)
            }
            else {
                render(view:'edit',model:[testResultType:testResultType])
            }
        }
        else {
            flash.message = "TestResultType not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
      def testResultType = new TestResultType()
      testResultType.properties = params
      return ['testResultType':testResultType]
    }

    def save = {
        def testResultType = new TestResultType()
        testResultType.properties = params
        if(testResultType.save()) {
            flash.message = "TestResultType ${testResultType.id} created."
            redirect(action:show,id:testResultType.id)
        }
        else {
            render(view:'create',model:[testResultType:testResultType])
        }
    }
}