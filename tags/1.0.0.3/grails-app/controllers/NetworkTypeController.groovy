class NetworkTypeController{
    def scaffold = NetworkType
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
        [ networkTypeList: NetworkType.findAllByType('Network') ]
    }

    def show = {
        [ networkType : NetworkType.get( params.id ) ]
    }

    def delete = {
        def networkType = NetworkType.get( params.id )
        if(networkType) {
            networkType.delete()
            flash.message = "NetworkType ${params.id} deleted."
            redirect(action:list)
        }
        else {
            flash.message = "NetworkType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def networkType = NetworkType.get( params.id )

        if(!networkType) {
                flash.message = "NetworkType not found with id ${params.id}"
                redirect(action:list)
        }
        else {
            return [ networkType : networkType ]
        }
    }

    def update = {
        def networkType = NetworkType.get( params.id )
        if(networkType) {
             networkType.properties = params
            if(networkType.save()) {
                flash.message = "NetworkType ${params.id} updated."
                redirect(action:show,id:networkType.id)
            }
            else {
                render(view:'edit',model:[networkType:networkType])
            }
        }
        else {
            flash.message = "NetworkType not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
      def networkType = new NetworkType()
      networkType.properties = params
      return ['networkType':networkType]
    }

    def save = {
        def networkType = new NetworkType()
        networkType.properties = params
        if(networkType.save()) {
            flash.message = "NetworkType ${networkType.id} created."
            redirect(action:show,id:networkType.id)
        }
        else {
            render(view:'create',model:[networkType:networkType])
        }
    }

}