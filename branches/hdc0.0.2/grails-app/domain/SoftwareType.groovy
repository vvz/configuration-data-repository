class SoftwareType
{
    String description
    Set softwares
    String type = 'Software'

    Date dateCreated
    Date lastUpdated

    static hasMany = [softwares:Software]
    static constraints = {
        description(nullable:false, blank:false)
        softwares(nullable:true)
    }

    static mapping = {
        table 'R_CI_TYPE'
    }

    String toString(){
        return "${description}"
    }
}