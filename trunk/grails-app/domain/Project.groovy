class Project implements java.io.Serializable{
    String name
    String description
    String ownerName
    String ownerEmail
    Set environments

    Date dateCreated
    Date lastUpdated

    static hasMany = [environments: Environment]
    static constraints = {
        name(nullable:false, blank:false)
        description(nullable:true, maxSize:1000)
        ownerName(nullable:true)
        ownerEmail(nullable:true, email:true)
        environments(nullable:true)
    }

    String toString() {
        return "${name}"
    }
}