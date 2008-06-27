class StatusReferenceController{
    def scaffold = StatusReference
    static accessControl = {
        // All actions require the 'Observer' role.
        role(name: 'Observer')

        // Alternatively, several actions can be specified.
        role(name: 'Administrator', only: [ 'create', 'edit', 'save', 'update' ])
    }
    /*
        def index = { redirect(action:list,params:params) }

        // the delete, save and update actions only accept POST requests
        def allowedMethods = [delete:'POST', save:'POST', update:'POST']

        def list = {
            if(!params.max)params.max = 10
            [ statusReferenceList: StatusReference.list( params ) ]
        }

        def show = {
            [ statusReference : StatusReference.get( params.id ) ]
        }

        def delete = {
            def statusReference = StatusReference.get( params.id )
            if(statusReference) {
                statusReference.delete()
                flash.message = "StatusReference ${params.id} deleted."
                redirect(action:list)
            }
            else {
                flash.message = "StatusReference not found with id ${params.id}"
                redirect(action:list)
            }
        }

        def edit = {
            def statusReference = StatusReference.get( params.id )

            if(!statusReference) {
                    flash.message = "StatusReference not found with id ${params.id}"
                    redirect(action:list)
            }
            else {
                return [ statusReference : statusReference ]
            }
        }

        def update = {
            def statusReference = StatusReference.get( params.id )
            if(statusReference) {
                 statusReference.properties = params
                if(statusReference.save()) {
                    flash.message = "StatusReference ${params.id} updated."
                    redirect(action:show,id:statusReference.id)
                }
                else {
                    render(view:'edit',model:[statusReference:statusReference])
                }
            }
            else {
                flash.message = "StatusReference not found with id ${params.id}"
                redirect(action:edit,id:params.id)
            }
        }

        def create = {
          def statusReference = new StatusReference()
          statusReference.properties = params
          return ['statusReference':statusReference]
        }

        def save = {
            def statusReference = new StatusReference()
            statusReference.properties = params
            if(statusReference.save()) {
                flash.message = "StatusReference ${statusReference.id} created."
                redirect(action:show,id:statusReference.id)
            }
            else {
                render(view:'create',model:[statusReference:statusReference])
            }
        }
    */

}