class StatusReference
{
    String name
    String description
    Set statuses
    static hasMany = [statuses: Status]
    //static belongsTo = []
    static constraints = {
        name(nullable: false, blank:false)
        description(nullable: true)
    }

    String toString() {
        return "${name}"
    }
}