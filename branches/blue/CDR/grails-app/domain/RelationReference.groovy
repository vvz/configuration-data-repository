class RelationReference implements java.io.Serializable{
    String name
    String description
    Set relations

    Date dateCreated
    Date lastUpdated
    
    static hasMany = [relations: Relation]
    static constraints = {
        name(nullable: false, blank:false)
        description(nullable: true)
        relations(nullable: true)
    }

    String toString() {
        return "${name}"
    }
}