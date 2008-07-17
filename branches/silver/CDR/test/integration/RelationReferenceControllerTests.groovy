class RelationReferenceControllerTests extends GroovyTestCase{
    public void testIndex(){
        def controller = new RelationReferenceController()
        controller.index()
        assertEquals "/relationReference/list", controller.response.redirectedUrl
    }
}
