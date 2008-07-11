class ProjectControllerTests extends GroovyTestCase{
    public void testIndex(){
        def controller = new ProjectController()
        controller.index()
        assertEquals "/project/list", controller.response.redirectedUrl
    }
}
