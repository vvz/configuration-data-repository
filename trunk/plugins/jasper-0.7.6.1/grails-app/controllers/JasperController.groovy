class JasperController {
    JasperService jasperService

    def index = {
        def statusStartDate = new Date()
        def statusEndDate = new Date()
        def statusDate = new Date()
        bindData(statusStartDate, params, "STATUS_DATE_START")
        bindData(statusEndDate, params, "STATUS_DATE_END")
        bindData(statusDate, params, "STATUS_DATE")
        params.STATUS_DATE_START = statusStartDate
        params.STATUS_DATE_END = statusEndDate
        params.STATUS_DATE = statusDate
        params.ENVIRONMENT_ID = new Integer(params.ENVIRONMENT_ID)
        def jasperFile
        if (grailsApplication.config.jasper.dir.reports) {
            jasperFile = grailsApplication.config.jasper.dir.reports
            if (!jasperFile.endsWith("/")) jasperFile += "/"
            if (!jasperFile.startsWith("/")) jasperFile = servletContext.getRealPath(jasperFile)
            println "jasper file 1: $jasperFile"
        }
        else {
            jasperFile = servletContext.getRealPath("${pluginContextPath}/reports/")
            println "pluginContextPath: $pluginContextPath"
            println "jasper file 2: $jasperFile"
        }

        params.JASPER_FILE = jasperFile

        def from = null
        if (params._from) {
            if (jasperService.existFrom(params._from)) {
                GroovyShell shell = new GroovyShell(grailsApplication.classLoader)
                from = shell.evaluate(params._from)
            }
        }
        switch (params._format) {
            case "PDF":
                createBinaryFile(jasperFile, jasperService.PDF_FORMAT, from, params, "pdf", "application/pdf")
                break
            case "HTML":
                render(text: jasperService.generateReport(jasperFile, jasperService.HTML_FORMAT, from, params), contentType: "text/html")
                break
            case "XML":
                render(text: jasperService.generateReport(jasperFile, jasperService.XML_FORMAT, from, params), contentType: "text/xml")
                break
            case "CSV":
                response.setHeader("Content-disposition", "attachment; filename=\"" + params._name + ".csv\"");
                render(text: jasperService.generateReport(jasperFile, jasperService.CSV_FORMAT, from, params), contentType: "text/csv")
                break
            case "XLS":
                createBinaryFile(jasperFile, jasperService.XLS_FORMAT, from, params, "xls", "application/vnd.ms-excel")
                break
            case "RTF":
                createBinaryFile(jasperFile, jasperService.RTF_FORMAT, from, params, "rtf", "text/rtf")
                break
            case "TEXT":
                render(text: jasperService.generateReport(jasperFile, jasperService.TEXT_FORMAT, from, params), contentType: "text")
                break
            default:
                throw new Exception(message(code: "jasper.controller.invalidFormat", args: [params._format]))
                break
        }
    }

    def createBinaryFile = {jasperFile, format, from, params, ext, mime ->
        def data = jasperService.generateReport(jasperFile, format, from, params).toByteArray()
        response.setHeader("Content-disposition", "attachment; filename=\"" + params._name + "." + ext + "\"");
        response.contentType = mime
        response.outputStream << data
    }

    def admin = {
        render(view: "admin")
    }
}