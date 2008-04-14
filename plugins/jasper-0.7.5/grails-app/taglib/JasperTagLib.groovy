class JasperTagLib {

  def jasperReport = { attrs, body ->
    validateAttributes(attrs)
    def appPath = grailsAttributes.getApplicationUri(request)
    def webAppPath = appPath + pluginContextPath
    def delimiter = attrs['delimiter'] ? attrs['delimiter'] : "|"

    out << """ 
      <script type="text/javascript">
        function create(link) {
          link.parentNode._format.value = link.title;
          link.parentNode.submit();
          return false;
        }
      </script>
      <form name="${attrs['jasper']}" action="${appPath}/jasper">
      <input type="hidden" name="_format" />
      <input type="hidden" name="_name" value="${attrs['name']}" />
      <input type="hidden" name="_file" value="${attrs['jasper']}" />
      ${delimiter} 
    """
    attrs['format'].split(",").each {
      out << """ 
        <a href="#" title="${it}" onClick="return create(this)">
        <img border="0" src="${webAppPath}/images/icons/${it}.gif" /></a>
        ${delimiter}
      """
    }
    out << "<b>${attrs['name']}</b>"
    out << body()
    out << "</form>"
  }
  
  private void validateAttributes(attrs) {
    //Verify the 'format' attribute
    def availableFormats = ["PDF","HTML","XML","CSV","XLS","RTF","TEXT"]
    attrs.format.toUpperCase().split(",").each {
      if (!availableFormats.contains(it)) {
        throw new Exception(message(code:"jasper.taglib.invalidFormatAttribute", args:["${it}", "${availableFormats}"]))
      }
    }
  }

}