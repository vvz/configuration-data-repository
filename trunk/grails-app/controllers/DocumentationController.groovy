class DocumentationController{
    /*def scaffold = Documentation*/

    static accessControl = {
        // All actions require the 'Observer' role.
        role(name: 'Observer')
    }
    def index = {  // temporary hack since g.actionSubmit does not work with multipart forms
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

        redirect(action: list, params: params)
    }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

    def list = {
        if (!params.max) params.max = 10
        [documentationList: Documentation.list(params)]
    }

    def show = {
        [documentation: Documentation.get(params.id)]
    }

    def delete = {
        def documentation = Documentation.get(params.id)
        if (documentation) {
            documentation.delete()
            flash.message = "Documentation ${params.id} deleted."
            redirect(action: list)
        }
        else {
            flash.message = "Documentation not found with id ${params.id}"
            redirect(action: list)
        }
    }

    def edit = {
        def documentation = Documentation.get(params.id)

        if (!documentation) {
            flash.message = "Documentation not found with id ${params.id}"
            redirect(action: list)
        }
        else {
            return [documentation: documentation]
        }
    }

    def update = {
        println "in update"
        def documentation = Documentation.get(params.id)
        if (documentation) {
            documentation.properties = params
            def upload = request.getFile('document')
            documentation.document = upload.getBytes()
            documentation.fileType = upload.getContentType()
            documentation.fileName = upload.getOriginalFilename()
            documentation.docVersion += 1 
            if (documentation.save()) {
                flash.message = "Documentation ${params.id} updated."
                redirect(action: show, id: documentation.id)
            }
            else {
                render(view: 'edit', model: [documentation: documentation])
            }
        }
        else {
            flash.message = "Documentation not found with id ${params.id}"
            redirect(action: edit, id: params.id)
        }
    }

    def create = {
        def documentation = new Documentation()
        documentation.properties = params
        return ['documentation': documentation]
    }

    def save = {
        def documentation = new Documentation()
        documentation.properties = params
        def upload = request.getFile('document')
        documentation.document = upload.getBytes()
        documentation.fileType = upload.getContentType()
        documentation.fileName = upload.getOriginalFilename()
        documentation.fileSize = upload.getSize()
        documentation.docVersion = 1
        if (documentation.save()) {
            flash.message = "Documentation ${documentation.id} created."
            redirect(action: show, id: documentation.id)
        }
        else {
            render(view: 'create', model: [documentation: documentation])
        }
    }

    def downloadDocument = {
        def documentation = Documentation.get(params.id)
        response.setHeader("Content-Type", "application/octet-stream;")
        response.setHeader("Content-Disposition", "attachment;filename = \"${documentation.fileName}\"")
        response.setHeader("Content-Length", "${documentation.fileSize}")
        response.outputStream << documentation.document
    }
}