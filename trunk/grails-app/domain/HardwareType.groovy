class HardwareType{

    String description
    String type = 'Hardware'

    static hasMany = [hardwares:Hardware]
    static constraints = {
        description(nullable:false, blank:false)
        hardwares(nullable:true)
    }

    static mapping = {
        table 'R_CI_TYPE'
    }

    String toString(){
        return "${description}"
    }
}