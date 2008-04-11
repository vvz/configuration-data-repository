class TestResult extends ConfigurationItem{
    byte[] document
    String fileType
    String fileName
    String fileSize
    TestResultType testResultType

    static belongsTo = TestResultType
    static constraints = {
        document(nullable:true)
        fileType(nullable:false)
        fileName(nullable:false)
        fileSize(nullable:false)
        testResultType(nullable:false)
    }

    String toString(){
        return "${name}"
    }
}