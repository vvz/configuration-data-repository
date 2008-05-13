class HardwareType{

    String description
    int order
    String type = 'Hardware'

    static hasMany = [hardwares:Hardware]
    static constraints = {
        description(nullable:false, blank:false)
        order(nullable:false, blank:false)
        hardwares(nullable:true)
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