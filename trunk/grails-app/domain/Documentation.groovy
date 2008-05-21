import java.sql.Blob
import java.sql.SQLException
import org.hibernate.Hibernate

class Documentation extends ConfigurationItem {
    int docVersion
    byte[] document
    Blob documentBlob
    String fileType
    String fileName
    String fileSize
    String title
    String abstraction
    DocumentationType documentationType

    Date dateCreated
    Date lastUpdated

    static transients = [ "document"] 
    static belongsTo = DocumentationType
    static constraints = {
        docVersion(nullable: true)
        document(nullable: true, maxSize: 10000000)
        documentBlob(nullable: true)
        fileType(nullable: true)
        fileName(nullable: true)
        fileSize(nullable: true)
        title(nullable: true)
        abstraction(nullable: true)
        documentationType(nullable: true)
    }

    static mapping = {
      documentBlob type:'blob'
   }

    def getDocument() {
        if (documentBlob == null)
            return null;
        return toByteArray(getDocumentBlob());
    }

    def setDocument(document) {
        setDocumentBlob(Hibernate.createBlob(document));
    }

    boolean equals(obj) {
        if (this == obj) return true;
        if (!obj || obj.class != this.class) return false;
        return id?.equals(obj.id)
    }

    int hashCode() {
        return id ? id.hashCode() : super.hashCode()
    }

    private byte[] toByteArray(Blob fromBlob) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            return toByteArrayImpl(fromBlob, baos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    private byte[] toByteArrayImpl(Blob fromBlob, ByteArrayOutputStream baos) {
        byte[] buf = new byte[4000];
        InputStream is = fromBlob.getBinaryStream();
        try {
            while (true) {
                int dataSize = is.read(buf);

                if (dataSize == -1)
                    break;
                baos.write(buf, 0, dataSize);
            }
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        return baos.toByteArray();
    }

    String toString() {
        return "${name}"
    }
}