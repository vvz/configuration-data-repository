class Status
{
    Date startDate
    Date endDate
    ConfigurationItem configurationItem
    StatusReference reference

    //static hasMany = []
    static belongsTo = [ConfigurationItem, StatusReference]
    static constraints = {
        startDate(nullable: false)
        endDate(nullable: false)
        configurationItem(nullable: false)
        reference(nullable: false)
    }

    String toString() {
        if (reference) {
            return "${reference.name}"
        } else {
            return ""
        }
    }
}