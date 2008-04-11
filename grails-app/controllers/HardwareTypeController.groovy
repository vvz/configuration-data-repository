class HardwareTypeController extends JsecAuthBase{
    /*def scaffold = HardwareType*/
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
        [ hardwareTypeList: HardwareType.findAllByType('Hardware') ]
    }

    def show = {
        [ hardwareType : HardwareType.get( params.id ) ]
    }

    def delete = {
        def hardwareType = HardwareType.get( params.id )
        if(hardwareType) {
            hardwareType.delete()
            flash.message = "HardwareType ${params.id} deleted."
            redirect(action:list)
        }
        else {
            flash.message = "HardwareType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def hardwareType = HardwareType.get( params.id )

        if(!hardwareType) {
                flash.message = "HardwareType not found with id ${params.id}"
                redirect(action:list)
        }
        else {
            return [ hardwareType : hardwareType ]
        }
    }

    def update = {
        def hardwareType = HardwareType.get( params.id )
        if(hardwareType) {
             hardwareType.properties = params
            if(hardwareType.save()) {
                flash.message = "HardwareType ${params.id} updated."
                redirect(action:show,id:hardwareType.id)
            }
            else {
                render(view:'edit',model:[hardwareType:hardwareType])
            }
        }
        else {
            flash.message = "HardwareType not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
      def hardwareType = new HardwareType()
      hardwareType.properties = params
      return ['hardwareType':hardwareType]
    }

    def save = {
        def hardwareType = new HardwareType()
        hardwareType.properties = params
        if(hardwareType.save()) {
            flash.message = "HardwareType ${hardwareType.id} created."
            redirect(action:show,id:hardwareType.id)
        }
        else {
            render(view:'create',model:[hardwareType:hardwareType])
        }
    }

}