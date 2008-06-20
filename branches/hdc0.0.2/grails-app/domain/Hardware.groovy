class Hardware extends ConfigurationItem{
    Date purchaseDate
    String macAddress
    String internetProtocolAddress
    String hostName
    HardwareType hardwareType

    Date dateCreated
    Date lastUpdated

    static belongsTo = HardwareType
    static constraints = {
        purchaseDate(nullable: true)
        macAddress(maxSize: 50, nullable: true)
        hostName(maxSize: 50, nullable: true)
        hardwareType(nullable:false, blank:false)
        internetProtocolAddress(nullable:true)
    }

    String toString() {
        return "${name}"
    }
}