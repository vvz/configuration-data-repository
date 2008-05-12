import org.apache.commons.codec.digest.DigestUtils

class BootStrap {

    def init = {servletContext ->
        def adminRole = JsecRole.findByName("Administrator")
        if (!adminRole) {
            adminRole = new JsecRole(name: 'Administrator')
            adminRole.save()
        }

        def observerRole = JsecRole.findByName('Observer')
        if (!observerRole) {
            observerRole = new JsecRole(name: 'Observer')
            observerRole.save()
        }

        // Create some users
        // Note that we store a hash of the user's password,
        // not the password itself.
        def admin = JsecUser.findByUsername('admin')
        if (!admin) {
            admin = new JsecUser(username: 'admin', passwordHash: DigestUtils.shaHex('changeit'))
            admin.save()
            new JsecUserRoleRel(user: admin, role: adminRole).save()
            new JsecUserRoleRel(user: admin, role: observerRole).save()
        }

        def tarzan = JsecUser.findByUsername("tarzan")
        if (!tarzan) {
            tarzan = new JsecUser(username: 'tarzan', passwordHash: DigestUtils.shaHex('password'))
            tarzan.save()
            new JsecUserRoleRel(user: tarzan, role: observerRole).save()
        }
        new RelationReference(name: "depends on", description: "depends on", order: 1).save()
        new RelationReference(name: "exists in", description: "exists in", order: 2).save()
        new RelationReference(name: "is documented by", description: "is documented by", order: 3).save()
        new RelationReference(name: "BOM", description: "exclusively owned by", order: 3).save()
        new RelationReference(name: "is installed on", description: "is installed on", order: 3).save()
        new RelationReference(name: "is baselined by", description: "is baselined by", order: 3).save()
        new RelationReference(name: "is certified by", description: "is certified by", order: 3).save()
        new RelationReference(name: "is prepared by", description: "is prepared by", order: 3).save()
        new RelationReference(name: "is configured by", description: "is configured by", order: 3).save()

        new StatusReference(name: "Planned", description: "Planned", order: 1).save()
        new StatusReference(name: "Implemented", description: "Implemented", order: 2).save()
        def active = new StatusReference(name: "Active", description: "Active", order: 3).save()
        new StatusReference(name: "Inactive", description: "Inactive", order: 4).save()
        new StatusReference(name: "Removed", description: "Removed", order: 5).save()
        new StatusReference(name: "Retired", description: "Retired", order: 6).save()

        Project aps = new Project(name: "APS").save()

        def testing = new Environment(name: "Testing", project: aps).save()


        def server = new HardwareType(description: 'Server', order: 1).save(flush: true)
        def os = new SoftwareType(description: 'Operating System', order: 1).save(flush: true)
        new SoftwareType(description: 'Patch Level', order: 1).save(flush: true)
        new SoftwareType(description: 'Database Server', order: 1).save(flush: true)
        new SoftwareType(description: 'Application Server', order: 1).save(flush: true)
        new SoftwareType(description: 'Web Server', order: 1).save(flush: true)
        new SoftwareType(description: 'Application', order: 1).save(flush: true)
        new SoftwareType(description: 'Database Schema', order: 1).save(flush: true)
        new SoftwareType(description: 'Script', order: 1).save(flush: true)
        def networkType = new NetworkType(description: 'Network', order: 1).save(flush: true)
        def documentationType = new DocumentationType(description: 'asdfasdf', order: 1).save(flush: true)
        new RequestType(description: 'Requirements', order: 1).save(flush: true)
        new TestResultType(description: 'Unit Tests', order: 1).save(flush: true)

        new Hardware(
                name: "Solutions",
                author: "Steve Holmes",
                purchaseDate: new Date(),
                hardwareType: server).save(flush: true)
        new Hardware(
                name: "Isis",
                author: "Steve Holmes",
                purchaseDate: new Date(),
                hardwareType: server).save(flush: true)

        def startDate = new Date() - 10
        (1..10).each {element ->
            new Status(
                    startDate: startDate,
                    endDate: startDate + 10000,
                    configurationItem: new Hardware(
                            name: "hardware${element}",
                            author: "Steve Holmes",
                            purchaseDate: new Date(),
                            hardwareType: server).save(flush: true),
                    reference: active).save(flush: true)
        }

        testing.configurationItems = Hardware.getAll()
        testing.save(flush: true)
        def hardwares = Hardware.getAll()
        hardwares.each {hardware ->
            (11..15).each {element ->
                new Status(
                        startDate: startDate,
                        endDate: startDate + 10000,
                        configurationItem: new Hardware(
                                name: "hardware ${element} ${hardware.id}",
                                author: "Steve Holmes",
                                purchaseDate: new Date(),
                                hardwareType: server,
                                parent: hardware).save(flush: true),
                        reference: active).save(flush: true)
            }
        }

        def windows = new Software(
                name: "Windows XP Professional",
                author: "Steve Holmes",
                softwareType: os)
        if (!windows.save(flush: true)) {
            windows.errors.each {error -> println error}
        }

        new Network(name: 'network 1', author: "Steve Holmes", networkType: networkType).save(flush: true)

        new Documentation(
                name: 'Document 1',
                author: 'Steve Holmes',
                version: 1,
                fileType: 'what',
                fileName: 'ever',
                documentationType: documentationType).save(flush: true)


    }

    def destroy = {
    }
} 