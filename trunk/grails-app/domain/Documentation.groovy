class Documentation extends ConfigurationItem{
    int docVersion
    byte[] document
    String fileType
    String fileName
    String fileSize
    String title
    String abstraction
    DocumentationType DocumentationType

    static belongsTo = DocumentationType
    static constraints = {
        docVersion(nullable:false)
        document(nullable:true)
        fileType(nullable:false)
        fileName(nullable:false)
        fileSize(nullable:false)
        title(nullable:true)
        abstraction(nullable:true)
        documentationType(nullable:false)
    }

    String toString(){
        return "${name}"
    }
}