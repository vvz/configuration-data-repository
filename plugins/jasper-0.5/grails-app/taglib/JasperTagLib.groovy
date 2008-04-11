class JasperTagLib {
  	
	def jasperReport = { attrs, body ->
		
		validateAttributes(attrs)
		def appPath = grailsAttributes.getApplicationUri(request)
		def webAppPath = appPath+pluginContextPath
		
		out << """ 
			<form id=\"${attrs['id']}\" name=\"${attrs['jasper']}\" action=\"${appPath}/jasper\">
			<input type=\"hidden\" name=\"format\"/>
			<input type=\"hidden\" name=\"file\" value=\"${attrs['jasper']}\"/> | 
			"""
		TreeSet formats = attrs['format'].split(",")
		formats.each{
			  	out << """ 
			  		<a href=\"#${attrs['jasper']}JasperReport\" onClick=\"document.getElementById('${attrs['id']}').format.value = '${it}';document.getElementById('${attrs['id']}').submit()\">
					<img width=\"16px\" height=\"16px\" border=\"0\" src=\"${webAppPath}/images/icons/${it}.gif\" />
					</a> |
					"""
		}
		out << "<a name=\"${attrs['jasper']}JasperReport\" href=\"#${attrs['jasper']}JasperReport\">${attrs['name']}</a>"
		out << body()
		out << "</form>"
	}
	
	private void validateAttributes(attrs) {
	  //Verify the 'id' attribute
	  if(attrs.id == null) 
	  	throw new Exception("The 'id' attribute in 'jasperReport' tag mustn't be 'null'")
	
	  //Verify the 'format' attribute
	  def availableFormats = ["CSV","HTML","PDF"]
	  attrs.format.toUpperCase().split(",").each{
	    if(!availableFormats.contains(it)){
	      throw new Exception("Value ${it} is a invalid format attribute. Only ${availableFormats} are permitted")
	    }
	  }
	  
	}
}