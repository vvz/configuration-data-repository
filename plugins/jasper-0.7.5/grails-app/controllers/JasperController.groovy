class JasperController {
  JasperService jasperService

  def index = {
    def jasperFile
    if (grailsApplication.config.jasper.dir.reports) {
      jasperFile = grailsApplication.config.jasper.dir.reports
      if (!jasperFile.endsWith("/")) jasperFile += "/"
      if (!jasperFile.startsWith("/")) jasperFile = servletContext.getRealPath(jasperFile)
    }
    else {
      jasperFile = servletContext.getRealPath("${pluginContextPath}/reports/")
    }

    switch (params._format) {
      case "PDF":
        createBinaryFile(jasperFile, jasperService.PDF_FORMAT, params, "pdf", "application/pdf")
        break
      case "HTML":
        render(text:jasperService.generateReport(jasperFile, jasperService.HTML_FORMAT, params), contentType:"text/html")
        break
      case "XML":
        render(text:jasperService.generateReport(jasperFile, jasperService.XML_FORMAT, params), contentType:"text/xml")
        break
      case "CSV":
        response.setHeader("Content-disposition", "attachment; filename=\"" + params._name + ".csv\"");
        render(text:jasperService.generateReport(jasperFile, jasperService.CSV_FORMAT, params), contentType:"text/csv")
        break
      case "XLS":
        createBinaryFile(jasperFile, jasperService.XLS_FORMAT, params, "xls", "application/vnd.ms-excel")
        break
      case "RTF":
        createBinaryFile(jasperFile, jasperService.RTF_FORMAT, params, "rtf", "text/rtf")
        break
      case "TEXT":
        render(text:jasperService.generateReport(jasperFile, jasperService.TEXT_FORMAT, params), contentType:"text")
        break
      default:
        throw new Exception(message(code:"jasper.controller.invalidFormat", args:[params._format]))
        break
    }
  }

  def createBinaryFile = { jasperFile, format, params, ext, mime ->
    def data = jasperService.generateReport(jasperFile, format, params).toByteArray()
    response.setHeader("Content-disposition", "attachment; filename=\"" + params._name + "." + ext + "\"");
    response.contentType = mime
    response.outputStream << data
  }

  def admin = {
    render(view:"admin")
  }

}
