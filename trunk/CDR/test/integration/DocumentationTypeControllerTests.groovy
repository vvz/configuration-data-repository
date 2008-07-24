class DocumentationTypeControllerTests extends GroovyTestCase {
   public void testSave() {
        def controller = new DocumentationTypeController()
        controller.params.description = "records"
        controller.save()
        assert controller.flash.message.startsWith("Documentation Type")
        assert controller.response.redirectedUrl.startsWith("/documentationType/show/")
        controller.response.redirectedUrl
    }

    public void testDelete() {
        def controller = new DocumentationTypeController()
        DocumentationType documentationType = new DocumentationType(description:"records")
        documentationType.save(flush:true)
        controller.params.id = documentationType.id
        controller.delete()
        assertEquals "/documentationType/list", controller.response.redirectedUrl
    }

    public void testFailDelete() {
        def controller = new DocumentationTypeController()
        DocumentationType documentationType = new DocumentationType(description:"records")
        documentationType.save(flush:true)
        new Documentation(name:"name",author:"author",documentationType:documentationType).save(flush:true)
        controller.params.id = documentationType.id
        controller.delete()
        assert controller.response.redirectedUrl.startsWith("/documentationType/show/")
    }
}
