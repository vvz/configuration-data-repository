class RequestType{
    String description
    int order
    String type = 'Change Request'
    Set requests

    static hasMany = [requests:ChangeRequest]
    static constraints = {
        description(nullable:false)
        order(nullable:false)
        requests(nullable:true)
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