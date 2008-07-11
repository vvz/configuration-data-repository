import org.springframework.web.multipart.commons.CommonsMultipartFile

/**
 * User: sholmes
 * Date: Jul 2, 2008
 * Time: 11:47:13 AM
 */
class RestFilters {
    def filters = {
        println "here 1"
        cancelServiceOrder(uri: '/serviceOrder') {
            before = {
                if (request.method == "DELETE"){
                    params._eventId_cancel = 'cancel'
                    println "here 2"
                } else if (request.method == "POST"){
                    params._eventId_persist = 'persist'
                    println "here 3"
                    println params
                    redirect(action: 'serviceOrder', params:params)
                }
            }
        }

        createConfigurationItem(uri: '/serviceOrder/configurationItem') {
            before = {
                if (request.method == "POST"){
                    params._eventId_createConfigurationItem ='createConfigurationItem'
                    println "here 4"
                    if(params.document){
                        def upload = request.getFile("document")
                        Map documentMap = ["document": upload.getBytes(),"contentType":upload.getContentType(), "fileName":upload.getOriginalFilename(), "fileSize":upload.getSize()]
                        session.setAttribute("documentMap",documentMap)
                        params.remove("document")
                    }
                    println params
                    redirect(action: 'serviceOrder', params:params)
                }
            }
        }

        modifyConfigurationItem(uri: '/serviceOrder/configurationItem/**') {
            before = {
                if (request.method == "PUT"){
                    params._eventId_modifyConfigurationItem = 'modifyConfigurationItem'
                    println "here 5"
                    redirect(action: 'serviceOrder', params:params)
                }
            }
        }

        createRelation(uri: '/serviceOrder/configurationItem/**/this') {
            before = {
                if (request.method == "POST"){
                    params._eventId_createRelation = 'createRelation'
                    println "here 6"
                    redirect(action: 'serviceOrder', params:params)
                }
            }
        }

        deleteRelation(uri: '/serviceOrder/configurationItem/**/this/**') {
            before = {
                if (request.method == "DELETE"){
                    params._eventId_deleteRelation = 'deleteRelation'
                    println "here 7"
                    redirect(action: 'serviceOrder', params:params)
                }
            }
        }
        
        createStatus(uri: '/serviceOrder/configurationItem/**/status') {
            before = {
                if (request.method == "POST"){
                    params._eventId_createStatus = 'createStatus'
                    println "here 9"
                    redirect(action: 'serviceOrder', params:params)
                }
            }
        }
    }
}