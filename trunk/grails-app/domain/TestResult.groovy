class TestResult extends ConfigurationItem{
    byte[] document
    String fileType
    String fileName
    String fileSize
    TestResultType testResultType

    static belongsTo = TestResultType
    static constraints = {
        document(nullable:true)
        fileType(nullable:true)
        fileName(nullable:true)
        fileSize(nullable:true)
        testResultType(nullable:false, blank:false)
    }

    String toString(){
        return "${name}"
    }
}