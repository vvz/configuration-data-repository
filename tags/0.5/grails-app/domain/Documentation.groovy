import java.sql.Blob
import org.hibernate.Hibernate
import com.delegata.utility.BlobUtil
import org.hibernate.lob.SerializableBlob

class Documentation extends ConfigurationItem implements java.io.Serializable {
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
    static belongsTo = [DocumentationType, Environment]
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
        if (this.document) return this.document
        else if(this.documentBlob) return BlobUtil.toByteArray(getDocumentBlob())
        else return null
    }

    def setDocument(document) {
        this.document = document
        this.documentBlob = Hibernate.createBlob(document)
    }

    def setDocumentBlob(documentBlob) {
        this.documentBlob = documentBlob
        document = BlobUtil.toByteArray(getDocumentBlob())
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