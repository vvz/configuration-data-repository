class Software extends ConfigurationItem{

    String versionNum
    String port
    String releaseNum
    SoftwareType softwareType

    static belongsTo = SoftwareType
    static constraints = {
        versionNum(nullable:true)
        port(nullable:true)
        releaseNum(nullable:true)
        softwareType(nullable:false, blank:false)
    }

    static mapping = {
        table 'software'
        columns {
            port column:'c_port'
                    releaseNum column:'c_release'
        }
    }

    String toString(){
        return "${name}"
    }
}