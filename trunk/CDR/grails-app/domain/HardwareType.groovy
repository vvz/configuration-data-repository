class HardwareType implements java.io.Serializable{

    String description
    String type = 'Hardware'

    Date dateCreated
    Date lastUpdated

    static hasMany = [hardwares:Hardware]
    static constraints = {
        description(nullable:false, blank:false, maxSize: 50)
        hardwares(nullable:true)
    }

    static mapping = {
        table 'R_CI_TYPE'
    }

    String toString(){
        return "${description}"
    }
}