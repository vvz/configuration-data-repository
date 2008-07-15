class RequestType implements java.io.Serializable{
    String description
    String type = 'Change Request'
    Set requests

    Date dateCreated
    Date lastUpdated

    static hasMany = [requests:ChangeRequest]
    static constraints = {
        description(nullable:false, blank:false, maxSize: 50)
        requests(nullable:true)
    }

    static mapping = {
        table 'R_CI_TYPE'
    }

    String toString(){
        return "${description}"
    }
}