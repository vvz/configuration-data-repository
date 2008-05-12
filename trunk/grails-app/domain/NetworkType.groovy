class NetworkType{
    String description
    int order
    String type = 'Network'
    Set networks

    static hasMany = [networks:Network]
    static constraints = {
        description(nullable:false)
        order(nullable:false)
        networks(nullable:true)
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