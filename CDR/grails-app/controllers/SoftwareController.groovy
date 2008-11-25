import grails.converters.*

class SoftwareController {
    /*def scaffold = Software*/
    static accessControl = {
        // All actions require the 'Observer' role.
        role(name: 'Observer')

        // Alternatively, several actions can be specified.
        role(name: 'Administrator', only: ['create', 'edit', 'save', 'update'])
    }
    def index = { redirect(action: list, params: params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

    def list = {
        if (!params.max) params.max = 10
        def c = Software.createCriteria()
        def softwareList = c.list(max: params?.max, offset: params?.offset) {
            environments {
                if(params.environmentId) {
                    eq('id', new Long(params.environmentId))
                }
            }

            statuses {
                reference {
                    if(params.active) {
                        eq('name', 'Active')
                    }
                }
            }
        }

        c = Software.createCriteria()
        def count = c.list{
            environments {
                if(params.environmentId) {
                    eq('id', new Long(params.environmentId))
                }
            }

            statuses {
                reference {
                    if(params.active) {
                        eq('name', 'Active')
                    }
                }
            }
        }

        /*if (!params.max) params.max = 10*/
        /*[softwareList: Software.list(params)]*/
        [softwareList: softwareList, environmentId: params.environmentId, active: params.active, count:count.size]
    }

    def dataTableJSON = {
        def software = Software.list(params)

        def formattedSoftware = software.collect {
            def environment = it.environments?.find {it}

            [
                    name: it.name,
                    status: it.statuses?.find {it.endDate > new Date()}?.reference?.name,
                    projectEnvironment: "${environment?.project?.name ? environment?.project?.name : ''} ${environment?.name ? environment?.name : ''}",
                    version: it.versionNum,
                    description: it.description,
                    lastUpdated: new java.text.SimpleDateFormat("MM-dd-yyyy").format(it.lastUpdated)
            ]
        }

        def data = [
                totalRecords: Software.count(),
                results: formattedSoftware
        ]
        println data

        render data as JSON
    }

    def show = {
        [software: Software.get(params.id)]
    }

    def delete = {
        def software = Software.get(params.id)
        if (software) {
            software.delete()
            flash.message = "Software ${params.id} deleted."
            redirect(action: list)
        }
        else {
            flash.message = "Software not found with id ${params.id}"
            redirect(action: list)
        }
    }

    def edit = {
        def software = Software.get(params.id)

        if (!software) {
            flash.message = "Software not found with id ${params.id}"
            redirect(action: list)
        }
        else {
            return [software: software]
        }
    }

    def update = {
        def software = Software.get(params.id)
        if (software) {
            if (Long.valueOf(software.version) != Long.valueOf(params.version)) {
                flash.message = "This record has been modified since you last saw it.  Please try updating again."
                redirect(action: show, id: software.id)
            } else {
                def circular = false
                if (params.get('parent.id') != 'null') {
                    def parent = Software.get(Long.parseLong(params.get('parent.id')))
                    log.debug "parent: ${parent}"
                    software.configurationItems.each {child ->
                        log.debug "child: ${child}"
                        if (child.id == parent.id) {
                            circular = true
                        }
                    }
                }

                if (circular) {
                    flash.message = "Cannot choose a child as a parent."
                    render(view: 'edit', model: [software: software])
                } else {
                    software.properties = params
                    if (software.save()) {
                        flash.message = "Software ${params.id} updated."
                        redirect(action: show, id: software.id)
                    }
                    else {
                        render(view: 'edit', model: [software: software])
                    }
                }
            }

        }
        else {
            flash.message = "Software not found with id ${params.id}"
            redirect(action: edit, id: params.id)
        }
    }

    def create = {
        def software = new Software()
        software.properties = params
        return ['software': software]
    }

    def save = {
        def software = new Software()
        software.properties = params
        if (software.parent) {
            software.parent.addToConfigurationItems(software)
        }
        if (software.save()) {
            flash.message = "Software ${software.id} created."
            redirect(action: show, id: software.id, params: ["parent.id": params.parent?.id])
        }
        else {
            render(view: 'create', model: [software: software])
        }
    }
}