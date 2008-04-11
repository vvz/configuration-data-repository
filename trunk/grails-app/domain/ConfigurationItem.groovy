class ConfigurationItem
{
    String name
    String description
    String author
    String ownerName
    String ownerEmail
    ConfigurationItem parent

    /*Set configurationItems
    Set environments
    Set statuses
    Set thisRelations
    Set thatRelations*/

    static hasMany = [thisRelations: Relation, thatRelations: Relation, environments: Environment, statuses: Status, configurationItems: ConfigurationItem]
    static mappedBy = [thisRelations: 'thisCI', thatRelations: 'thatCI', configurationItems: 'parent']
    static belongsTo = [Environment, ConfigurationItem]
    static constraints = {
        name(nullable: false)
        description(nullable: true, maxSize: 1000)
        author(nullable: false)
        ownerName(nullable: true)
        ownerEmail(nullable: true, email: true)
        parent(nullable: true)
        configurationItems(nullable: true)
        environments(nullable: true)
        statuses(nullable: true)
        thisRelations(nullable: true)
        thatRelations(nullable: true)
    }

    static mapping = {
        //tablePerHierarchy false
        table 'configuration_item'
        columns {
            environments lazy: false
            parent lazy: false, column: 'bom'
        }
    }

    String toString() {
        return "${name}"
    }
}