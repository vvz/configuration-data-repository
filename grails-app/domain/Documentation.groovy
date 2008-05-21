import java.sql.Blob
import org.hibernate.Hibernate
import com.delegata.utility.BlobUtil

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

    static transients = ["document"]
    static belongsTo = DocumentationType
    static constraints = {
        docVersion(nullable: true)
        document(nullable: true)
        documentBlob(nullable: true)
        fileType(nullable: true)
        fileName(nullable: true)
        fileSize(nullable: true)
        title(nullable: true)
        abstraction(nullable: true)
        documentationType(nullable: true)
    }

    static mapping = {
        documentBlob type: 'blob'
    }

    def getDocument() {
        if (documentBlob == null)
            return null;
        return BlobUtil.toByteArray(getDocumentBlob());
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

    String toString() {
        return "${name}"
    }
}