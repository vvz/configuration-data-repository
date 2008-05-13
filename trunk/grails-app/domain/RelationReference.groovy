class RelationReference
{
    String name
    String description
    Set relations
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