class Project
{
    String name
    String description
    String ownerName
    String ownerEmail
    Set environments
    static hasMany = [environments: Environment]
    //static belongsTo = []
    static constraints = {
        name(nullable:false)
        description(nullable:true, maxSize:1000)
        ownerName(nullable:true)
        ownerEmail(nullable:true, email:true)
        environments(nullable:true)
    }

    String toString() {
        return "${name}"
    }
}