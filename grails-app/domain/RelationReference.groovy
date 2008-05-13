class RelationReference
{
    String name
    String description
    int order
    Set relations
    static hasMany = [relations: Relation]
    static constraints = {
        name(nullable: false, blank:false)
        description(nullable: true)
        order(nullable: false, blank:false)
        relations(nullable: true)
    }

    static mapping = {
        columns {
            order column: 'order_num'
        }
    }

    String toString() {
        return "${name}"
    }
}