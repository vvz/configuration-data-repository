class TestResultController{
    /*def scaffold = TestResult*/
    static accessControl = {
        // All actions require the 'Observer' role.
        role(name: 'Observer')
    }
    def index = {
        if (params["_action_Save"]) {
            save()
        }
        else if (params["_action_Update"]) {
            update()
        }
        else if (params["_action_Delete"]) {
            delete()
        }
        else {
            redirect(action: list, params: params)
        }
    }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

    def list = {
        if (!params.max) params.max = 10
        [testResultList: TestResult.list(params)]
    }

    def show = {
        [testResult: TestResult.get(params.id)]
    }

    def delete = {
        def testResult = TestResult.get(params.id)
        if (testResult) {
            testResult.delete()
            flash.message = "TestResult ${params.id} deleted."
            redirect(action: list)
        }
        else {
            flash.message = "TestResult not found with id ${params.id}"
            redirect(action: list)
        }
    }

    def edit = {
        def testResult = TestResult.get(params.id)

        if (!testResult) {
            flash.message = "TestResult not found with id ${params.id}"
            redirect(action: list)
        }
        else {
            return [testResult: testResult]
        }
    }

    def update = {
        def testResult = TestResult.get(params.id)
        if (testResult) {
            testResult.properties = params
            def upload = request.getFile('document')
            testResult.document = upload.getBytes()
            testResult.fileType = upload.getContentType()
            testResult.fileName = upload.getOriginalFilename()
            if (testResult.save()) {
                flash.message = "TestResult ${params.id} updated."
                redirect(action: show, id: testResult.id)
            }
            else {
                render(view: 'edit', model: [testResult: testResult])
            }
        }
        else {
            flash.message = "TestResult not found with id ${params.id}"
            redirect(action: edit, id: params.id)
        }
    }

    def create = {
        def testResult = new TestResult()
        testResult.properties = params
        return ['testResult': testResult]
    }

    def save = {
        def testResult = new TestResult()
        testResult.properties = params
        def upload = request.getFile('document')
        testResult.document = upload.getBytes()
        testResult.fileType = upload.getContentType()
        testResult.fileName = upload.getOriginalFilename()
        testResult.fileSize = upload.getSize()
        if (testResult.save()) {
            flash.message = "TestResult ${testResult.id} created."
            redirect(action: show, id: testResult.id)
        }
        else {
            render(view: 'create', model: [testResult: testResult])
        }
    }
    
    def downloadDocument = {
        def testResult = TestResult.get(params.id)
        response.setHeader("Content-Type", "application/octet-stream;")
        response.setHeader("Content-Disposition", "attachment;filename = \"${testResult.fileName}\"")
        response.setHeader("Content-Length", "${testResult.fileSize}")
        response.outputStream << testResult.document
    }
}