import com.delegata.domain.ServiceOrder
import org.hibernate.Hibernate

class ServiceOrderService implements java.io.Serializable{

    boolean transactional = true

    def persistServiceOrder(ServiceOrder serviceOrder) {
        serviceOrder.configurationItems.each { ci ->
            println ci
            if(ci.metaClass.hasProperty(ci, "document")) ci.document = ci.document
            ci.save(flush:true)

            if(ci.environments){
                ci.environments.each{ environment ->
                    environment.configurationItems << ci
                    environment.save()   
                }
            }
        }
        serviceOrder.relations.each {
            println "it.thisCI?.id: ${it.thisCI?.id}"
            println "it.thatCI?.id: ${it.thatCI?.id}"
            println "it.thatCI: ${it.thatCI}"
            it.save()
        }
        Date date = new Date();
        serviceOrder.statusus.each {newStatus ->
            newStatus.configurationItem.statuses.each {status ->
                if (status.endDate > date) {
                    status.endDate = date
                    status.save()
                }
            }

            newStatus.startDate = date
            newStatus.endDate = date + 100000
            newStatus.save()
        }
    }
}