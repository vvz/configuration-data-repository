import grails.converters.XML

class RelationReferenceController{

    static accessControl = {
        // All actions require the 'Observer' role.
        role(name: 'Observer')

        // Alternatively, several actions can be specified.
        role(name: 'Administrator', only: [ 'create', 'edit', 'save', 'update' ])
    }
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST', referenceList:'GETrender RelationReference.list( params ) as XML ']

    def list = {
        if(!params.max) params.max = 10
        [ relationReferenceList: RelationReference.list( params ) ]
    }

    def show = {
        [ relationReference : RelationReference.get( params.id ) ]
    }

    def delete = {
        def relationReference = RelationReference.get( params.id )
        if(relationReference) {
            relationReference.delete()
            flash.message = "RelationReference ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "RelationReference not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def relationReference = RelationReference.get( params.id )

        if(!relationReference) {
            flash.message = "RelationReference not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ relationReference : relationReference ]
        }
    }

    def update = {
        def relationReference = RelationReference.get( params.id )
        if(relationReference) {
            relationReference.properties = params
            if(!relationReference.hasErrors() && relationReference.save()) {
                flash.message = "RelationReference ${params.id} updated"
                redirect(action:show,id:relationReference.id)
            }
            else {
                render(view:'edit',model:[relationReference:relationReference])
            }
        }
        else {
            flash.message = "RelationReference not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def relationReference = new RelationReference()
        relationReference.properties = params
        return ['relationReference':relationReference]
    }

    def save = {
        def relationReference = new RelationReference(params)
        if(!relationReference.hasErrors() && relationReference.save()) {
            flash.message = "RelationReference ${relationReference.id} created"
            redirect(action:show,id:relationReference.id)
        }
        else {
            render(view:'create',model:[relationReference:relationReference])
        }
    }

    def referenceList = {
        def reference = new RelationReference(params)
        render StatusReference.findAll(reference) as XML
    }
}