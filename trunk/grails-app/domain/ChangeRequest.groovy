class ChangeRequest extends ConfigurationItem{

    byte[] document
    String fileType
    String fileName
    String fileSize
    RequestType requestType

    static belongsTo = RequestType
    static constraints = {
        document(nullable:true)
        fileType(nullable:false)
        fileName(nullable:false)
        fileSize(nullable:false)
        requestType(nullable:false)
    }

    String toString(){
        return "${name}"
    }
}