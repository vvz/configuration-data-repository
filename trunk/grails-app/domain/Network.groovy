class Network extends ConfigurationItem{
    String internetProtocolAddress
    String macAddress
    String element
    String location

    NetworkType networkType

    static constraints = {
        internetProtocolAddress(nullable:true)
        macAddress(nullable:true)
        element(nullable:true)
        location(nullable:true)
        networkType(nullable:false)
    }
    static mapping = {
        tablePerHierarchy false
    }

    String toString(){
        return "${name}"
    }
}