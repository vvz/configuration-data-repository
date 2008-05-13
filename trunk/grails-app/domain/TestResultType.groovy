class TestResultType{
    String description
    Set results
    String type = 'Test Result'

    static hasMany = [results:TestResult]
    static constraints = {
        description(nullable:false, blank:false)
        results(nullable:true)
    }

    static mapping = {
        table 'R_CI_TYPE'
    }

    String toString(){
        return "${description}"
    }
}