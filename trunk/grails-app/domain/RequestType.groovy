class RequestType{
    String description
    String type = 'Change Request'
    Set requests

    static hasMany = [requests:ChangeRequest]
    static constraints = {
        description(nullable:false, blank:false)
        requests(nullable:true)
    }

    static mapping = {
        table 'R_CI_TYPE'
    }

    String toString(){
        return "${description}"
    }
}