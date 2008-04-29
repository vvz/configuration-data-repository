class ReportController {

    def index = { }

    def diffReport = {
        render(view: 'diffReport', model:[projects:Project.list(),environments:Environment.list()])
    }

    def environmentReport = {
        render(view: 'environmentReport', model:[projects:Project.list(),environments:Environment.list()])
    }

    def environmentSelect = {
        def project = Project.get(params.project.id)
        def environments = project ? Environment.findAllByProject(project) : []
        return [environments:environments]
    }
}