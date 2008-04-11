class DocumentationType{

    String description
    int order
    String type = 'Documentation'

    Set documents

    static hasMany = [documents:Documentation]
    static constraints = {
        description(nullable:false)
        order(nullable:false)
        documents(nullable:true)
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