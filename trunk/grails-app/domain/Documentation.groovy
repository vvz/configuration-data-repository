class Documentation extends ConfigurationItem{
    int docVersion
    byte[] document
    String fileType
    String fileName
    String fileSize
    String title
    String abstraction
    DocumentationType documentationType

    static belongsTo = DocumentationType
    static constraints = {
        docVersion(nullable:true)
        document(nullable:true)
        fileType(nullable:true)
        fileName(nullable:true)
        fileSize(nullable:true)
        title(nullable:true)
        abstraction(nullable:true)
        documentationType(nullable:true)
    }

    String toString(){
        return "${name}"
    }
}