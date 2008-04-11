class JasperController {
	JasperService jasperService
	def index = {
   		def path = pluginContextPath+"/reports/"+params.file+".jasper"
   		def jasperFile = servletContext.getResource(path)
   		if(jasperFile == null){
   		  throw new FileNotFoundException("\"${params.file}.jasper\" file must be in reports repository.")
   		}
      	switch(params.format){
      	  case "PDF":
	      	createPdfFile(jasperService.generateReport(jasperFile,jasperService.PDF_FORMAT,params).toByteArray(),params.file)
      	  	break
      	  case "HTML":
        	render(text:jasperService.generateReport(jasperFile,jasperService.HTML_FORMAT,params),contentType:"text/html")
          	break
      	  case "CSV":
	        render(text:jasperService.generateReport(jasperFile,jasperService.CSV_FORMAT,params),contentType:"text")
          	break
          default:
            throw new Exception("Invalid format")
          	break
      	}
	}
  
  	def createPdfFile = { contentBinary, fileName ->
  		response.setHeader("Content-disposition", "attachment; filename=" +
  	    fileName + ".pdf");
    	response.contentType = "application/pdf"
    	response.outputStream << contentBinary
  	}
  
}