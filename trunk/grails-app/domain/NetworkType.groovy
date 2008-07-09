class NetworkType implements java.io.Serializable{
    String description
    String type = 'Network'
    Set networks

    Date dateCreated
    Date lastUpdated

    static hasMany = [networks:Network]
    static constraints = {
        description(nullable:false, blank:false)
        networks(nullable:true)
    }

    static mapping = {
        table 'R_CI_TYPE'
    }

    String toString(){
        return "${description}"
    }
}