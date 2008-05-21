import com.delegata.utility.BlobUtil
import org.hibernate.Hibernate
import java.sql.Blob

class ChangeRequest extends ConfigurationItem{

    byte[] document
    Blob documentBlob
    String fileType
    String fileName
    String fileSize
    RequestType requestType

    Date dateCreated
    Date lastUpdated

    static transients = ["document"]
    static belongsTo = RequestType
    static constraints = {
        document(nullable:true)
        documentBlob(nullable: true)
        fileType(nullable:true)
        fileName(nullable:true)
        fileSize(nullable:true)
        requestType(nullable:false, blank:false)
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

    String toString(){
        return "${name}"
    }
}