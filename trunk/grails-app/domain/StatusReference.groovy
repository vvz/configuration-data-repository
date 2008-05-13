class StatusReference
{
    String name
    String description
    int order
    Set statuses
    static hasMany = [statuses: Status]
    //static belongsTo = []
    static constraints = {
        name(nullable: false, blank:false)
        description(nullable: true)
        order(nullable: false, blank:false)
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