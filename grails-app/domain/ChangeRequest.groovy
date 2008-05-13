class ChangeRequest extends ConfigurationItem{

    byte[] document
    String fileType
    String fileName
    String fileSize
    RequestType requestType

    Date dateCreated
    Date lastUpdated

    static belongsTo = RequestType
    static constraints = {
        document(nullable:true)
        fileType(nullable:true)
        fileName(nullable:true)
        fileSize(nullable:true)
        requestType(nullable:true)
    }

    String toString(){
        return "${name}"
    }
}