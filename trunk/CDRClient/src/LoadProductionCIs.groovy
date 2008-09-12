import com.delegata.cdr.client.util.*
import com.delegata.cdr.client.domain.*
import groovy.util.slurpersupport.GPathResult

/**
 * User: HOLMESS
 * Date: Aug 26, 2008
 * Time: 12:49:25 PM
 */

//def root = "http://solutions.delegata.com:8086/CDR"
def username = "admin"
def password = "changeit"

//def root = "http://cdr.caldoj.net/CDR"
def root = "http://localhost:8080/CDR"
def projectName = "AV-SRF"
//def environmentFrom = "AV-SRF Staging"
def environment = " 	AV-SRF Pseudo-Production"
def statusName = "Active"
//def fromStatusName = "Active"
def configurationItems = [
    new Software(name: "AV-SRF", softwareType: new SoftwareType(description:"CJIS app")),
    new Software(name: "AVSRF Data", softwareType: new SoftwareType(description:"CJISAPP Data")),
    new Software(name: "AVSRF Code", softwareType: new SoftwareType(description:"CJISAPP Code")),
    new Software(name: "MKE_EPR", softwareType: new SoftwareType(description:"mke")),
    new Software(name: "MKE_EPR Data", softwareType: new SoftwareType(description:"mke data")),
    new Software(name: "MKE_EPR Code", softwareType: new SoftwareType(description:"mke code")),
    new Software(name: "MKE_QCF", softwareType: new SoftwareType(description:"mke")),
    new Software(name: "MMKE_QCF Data", softwareType: new SoftwareType(description:"mke data")),
    new Software(name: "MKE_QCF Code", softwareType: new SoftwareType(description:"mke code")),
    new Software(name: "MKE_XVC", softwareType: new SoftwareType(description:"mke")),
    new Software(name: "MKE_XVC Data", softwareType: new SoftwareType(description:"mke data")),
    new Software(name: "MKE_XVC Code", softwareType: new SoftwareType(description:"mke code"))
]

def relations = [:]
def statusus = []
CDRClient client = new CDRClient(root: root)
client.authenticate(username, password)
println "client.body: ${client.body}"
client.authenticate(username, password)
println "client.body: ${client.body}"

configurationItems.eachWithIndex{ ci, i ->
    client.getConfigurationItem(new Software(name: ci.name, category: ci.category), environment, projectName, statusName, ci.softwareType.description)
    println "client.body: ${client.body}"
    GPathResult build = new XmlSlurper().parseText(client.body)
    configurationItems[i].name = build['name'].toString()
    configurationItems[i].description = build.description
    configurationItems[i].author = build.author
    configurationItems[i].ownerName = build.ownerName
    configurationItems[i].ownerEmail = build.ownerEmail
    configurationItems[i].parent = new ConfigurationItem(id: build['parent'].@id)
    configurationItems[i].versionNum = build.versionNum
    configurationItems[i].port = build.port
    configurationItems[i].releaseNum = build.releaseNum
    configurationItems[i].softwareType = new SoftwareType(id: build.softwareType.@id)
    configurationItems[i].environmentName = environment
    configurationItems[i].projectName = projectName

    build.thisRelations.relation.each {
        println "this relation: $it"
        relations.put("${it.thisCI.@id}${it.reference.name}${i}", new Relation(thisCI: new ConfigurationItem(id: "${it.thisCI.@id}"), reference: new RelationReference(name: it.reference.name), listId: i))
        println client.body
    }

    build.thatRelations.relation.each {
        println "that relation: $it"
        relations.put("${it.thisCI.@id}${it.reference.name}${i}", new Relation(thisCI: new ConfigurationItem(id: "${it.thisCI.@id}"), reference: new RelationReference(name: it.reference.name), listId: i)) 
        println client.body
    }

    statusus << new Status(configurationItem: new Software(id: build.@id), reference: new StatusReference(name: statusName, description: statusName), listId: i)
    statusus << new Status(reference: new StatusReference(name: statusName, description: statusName), listId: i)
}

client.startServiceOrder()

configurationItems.each {
    println "Creating Configuration Item: $it"
    client.createConfigurationItem(it)
    println "client.body: ${client.body}"
}

relations.each {key, value ->
    println "Creating Relation: $value"
    client.createRelation(value, value.listId)
    println "client.body: ${client.body}"
}

statusus.each {
    println "Creating Status: $it"
    client.createStatus(it, it.listId)
    println "client.body: ${client.body}"
}

client.persistServiceOrder()
println "client.body: ${client.body}"
client.signOut()
println "client.body: ${client.body}"