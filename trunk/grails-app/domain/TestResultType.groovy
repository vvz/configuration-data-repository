class TestResultType{
    String description
    int order
    Set results
    String type = 'Test Result'

    static hasMany = [results:TestResult]
    static constraints = {
        description(nullable:false, blank:false)
        order(nullable:false, blank:false)
        results(nullable:true)
    }

    static mapping = {
        table 'R_CI_TYPE'
        columns {
            order column:'order_num'
        }
    }

    String toString(){
        return "${description}"
    }
}