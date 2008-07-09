class StatusReference implements java.io.Serializable{
    String name
    String description
    Set statuses

    Date dateCreated
    Date lastUpdated

    static hasMany = [statuses: Status]
    static constraints = {
        name(nullable: false, blank:false)
        description(nullable: true)
    }

    String toString() {
        return "${name}"
    }
}