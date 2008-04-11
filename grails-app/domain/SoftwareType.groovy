class SoftwareType
{
    String description
    int order
    Set softwares
    String type = 'Software'

    static hasMany = [softwares:Software]
    static constraints = {
        description(nullable:false)
        order(nullable:false)
        softwares(nullable:true)
    }

    static mapping = {
        table 'R_CI_TYPE'
        columns {
            order column:'order_num'
        }
    }

    String toString(){
        return "${description}"
    }
}