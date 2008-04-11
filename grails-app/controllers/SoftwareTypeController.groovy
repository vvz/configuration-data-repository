class SoftwareTypeController extends JsecAuthBase{
    /*def scaffold = SoftwareType*/
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
        [ softwareTypeList: SoftwareType.findAllByType('Software') ]
    }

    def show = {
        [ softwareType : SoftwareType.get( params.id ) ]
    }

    def delete = {
        def softwareType = SoftwareType.get( params.id )
        if(softwareType) {
            softwareType.delete()
            flash.message = "SoftwareType ${params.id} deleted."
            redirect(action:list)
        }
        else {
            flash.message = "SoftwareType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def softwareType = SoftwareType.get( params.id )

        if(!softwareType) {
                flash.message = "SoftwareType not found with id ${params.id}"
                redirect(action:list)
        }
        else {
            return [ softwareType : softwareType ]
        }
    }

    def update = {
        def softwareType = SoftwareType.get( params.id )
        if(softwareType) {
             softwareType.properties = params
            if(softwareType.save()) {
                flash.message = "SoftwareType ${params.id} updated."
                redirect(action:show,id:softwareType.id)
            }
            else {
                render(view:'edit',model:[softwareType:softwareType])
            }
        }
        else {
            flash.message = "SoftwareType not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
      def softwareType = new SoftwareType()
      softwareType.properties = params
      return ['softwareType':softwareType]
    }

    def save = {
        def softwareType = new SoftwareType()
        softwareType.properties = params
        if(softwareType.save()) {
            flash.message = "SoftwareType ${softwareType.id} created."
            redirect(action:show,id:softwareType.id)
        }
        else {
            render(view:'create',model:[softwareType:softwareType])
        }
    }

}