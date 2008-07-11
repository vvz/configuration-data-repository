import grails.converters.*

class HardwareController {
    /*def scaffold = Hardware*/
    /*static accessControl = {
        // All actions require the 'Observer' role.
        role(name: 'Observer')
    }*/
    def index = {redirect(action: list, params: params)}

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

    def list = {
        if (!params.max) params.max = 10
        [hardwareList: Hardware.list(params)]
    }

    def show = {
        def hardware = Hardware.get(params.id)
        [hardware: hardware]
    }

    def delete = {
        Hardware hardware = Hardware.get(params.id)
        if (hardware) {
            def name = hardware.name
            hardware.environments.each {environment ->
                environment.removeFromConfigurationItems(hardware).save()
            }

            hardware.delete()

            flash.message = "Hardware ${name} deleted."
            redirect(action: list)
        }
        else {
            flash.message = "Hardware not found with id ${params.id}"
            redirect(action: list)
        }
    }

    def edit = {
        def hardware = Hardware.get(params.id)

        if (!hardware) {
            flash.message = "Hardware not found with id ${params.id}"
            redirect(action: list)
        }
        else {
            return [hardware: hardware]
        }
    }

    def update = {
        withFormat {
            xml {
                def hardware = Hardware.findByName(params.hardware.name)
                hardware.properties = params['hardware']
                println "$hardware"
                if (hardware.save()) {
                    render hardware as XML
                } else {
                    def errors = hardware.errors.allErrors.collect {g.message(error: it)}
                    render(contentType: "text/xml") {
                        error {
                            for (err in errors) {
                                message(error: err)
                            }
                        }
                    }
                }
            }
            form{
                def hardware = Hardware.get(params.id)
                hardware.properties = params
                if (hardware.save()) {
                    flash.message = "Hardware ${params.id} updated."
                    println "$hardware"
                    redirect(action: show, id: hardware.id)
                } else {
                    println "$hardware"
                    render(view: 'edit', model: [hardware: hardware])
                }
            }
        }
    }

    def create = {
        def hardware = new Hardware()
        hardware.properties = params
        return ['hardware': hardware]
    }

    def save = {
        withFormat {
            xml {
                def hardware = new Hardware(params['hardware'])
                if (hardware.parent) {
                    hardware.parent.addToConfigurationItems(hardware)
                }
                if (hardware.save()) {
                    render hardware as XML
                } else {
                    def errors = hardware.errors.allErrors.collect {g.message(error: it)}
                    render(contentType: "text/xml") {
                        error {
                            for (err in errors) {
                                message(error: err)
                            }
                        }
                    }
                }
            }
            form {
                def hardware = new Hardware(params)
                if (hardware.parent) {
                    hardware.parent.addToConfigurationItems(hardware)
                }
                if (hardware.save()) {
                    flash.message = "Hardware ${hardware.id} created."
                    redirect(action: show, id: hardware.id, params: ["parent.id": params.parent?.id])
                } else {
                    render(view: 'create', model: [hardware: hardware])
                }
            }
        }
    }
}