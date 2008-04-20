import java.io.ByteArrayOutputStream

import net.sf.jasperreports.engine.JRExporter
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JRExporterParameter
import net.sf.jasperreports.engine.export.JRCsvExporter
import net.sf.jasperreports.engine.export.JRHtmlExporter
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter
import net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.engine.export.JRRtfExporter
import net.sf.jasperreports.engine.export.JRTextExporter
import net.sf.jasperreports.engine.export.JRTextExporterParameter
import net.sf.jasperreports.engine.export.JRXlsExporter
import net.sf.jasperreports.engine.export.JRXmlExporter

class JasperService {
  int PDF_FORMAT = 1;
  int HTML_FORMAT = 2;
  int XML_FORMAT = 3;
  int CSV_FORMAT = 4;
  int XLS_FORMAT = 5;
  int RTF_FORMAT = 6;
  int TEXT_FORMAT = 7;

  boolean transactional = true
  javax.sql.DataSource dataSource

  def generateReport = { jasperFile, format, parameters ->
    JRExporter exporter
    switch (format) {
      case PDF_FORMAT:
        exporter = new JRPdfExporter()
        break
      case HTML_FORMAT:
        exporter = new JRHtmlExporter()
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false)
        break
      case XML_FORMAT:
        exporter = new JRXmlExporter()
        break
      case CSV_FORMAT:
        exporter = new JRCsvExporter()
        break
      case XLS_FORMAT:
        exporter = new JRXlsExporter()
        break
      case RTF_FORMAT:
        exporter = new JRRtfExporter()
        break
      case TEXT_FORMAT:
        exporter = new JRTextExporter()
        exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, 80)
        exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, 60)
        break
      default:
        throw new Exception("Unknown Report Format")
    }

    ByteArrayOutputStream byteArray = new ByteArrayOutputStream()
    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArray)

    JasperPrint jasperPrint
    def conn = dataSource.getConnection()

    try {
      if (new File("${jasperFile}${parameters._file}.jasper").canRead()) {
        jasperPrint = JasperFillManager.fillReport("${jasperFile}${parameters._file}.jasper", parameters, conn)
      }
      else if (new File("${jasperFile}${parameters._file}.jrxml").canRead()) {
        jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport("${jasperFile}${parameters._file}.jrxml"), parameters, conn)
      }
      else {
        throw new Exception("Unknown Report File: ${jasperFile}${parameters._file}.jasper or .jrxml")
      }
    }
    catch(Exception e){
        e.printStackTrace()
    }
    finally {
      conn.close()
    }

    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint)

    exporter.exportReport()
    return byteArray
  }

}
