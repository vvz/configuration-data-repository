import java.io.ByteArrayOutputStream
import java.io.InputStream

import net.sf.jasperreports.engine.JRExporter
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JRExporterParameter
import net.sf.jasperreports.engine.export.JRCsvExporter
import net.sf.jasperreports.engine.export.JRHtmlExporter
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter
import net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.engine.export.JRXlsExporter
import net.sf.jasperreports.engine.export.JRXmlExporter

class JasperService {
  
	boolean transactional = true
  
	int PDF_FORMAT = 1;

	int HTML_FORMAT = 2;

	int TEXT_FORMAT = 3;

	int CSV_FORMAT = 4;

	int XLS_FORMAT = 5;

  	javax.sql.DataSource dataSource
	
	def generateReport = {jasperFile, format, parameters ->
		InputStream input = jasperFile.openStream()
		JRExporter exporter
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream()
		JasperPrint jasperPrint = JasperFillManager.fillReport(input, parameters,dataSource.getConnection())
		switch (format) {
			case PDF_FORMAT:
				exporter = new JRPdfExporter()
				break
			case HTML_FORMAT:
				exporter = new JRHtmlExporter()
				exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false)
				break
			case CSV_FORMAT:
				exporter = new JRCsvExporter()
				break
			case TEXT_FORMAT:
				exporter = new JRXmlExporter()
				break
			case XLS_FORMAT:
				exporter = new JRXlsExporter()
				break
			default:
				throw new Exception("Unknown report format")
		}
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArray)
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint)
		exporter.exportReport()
		return byteArray
	}
  	
}

