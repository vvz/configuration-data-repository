class Relation
{
    RelationReference reference
    ConfigurationItem thisCI
    ConfigurationItem thatCI

    //static hasMany = []
    static belongsTo = [ConfigurationItem, RelationReference]
    static constraints = {
        thisCI(nullable: false)
        reference(nullable: false)
        thatCI(nullable: false)
    }

    String toString() {
        if (thisCI && reference && thatCI) {
            return "${thisCI.name} ${reference.name} ${thatCI.name}"
        } else {
            return ""
        }
    }
}