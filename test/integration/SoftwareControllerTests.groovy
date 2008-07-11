class SoftwareControllerTests extends GroovyTestCase{
  public void testIndex(){
        def controller = new SoftwareController()
        controller.index()
        assertEquals "/software/list", controller.response.redirectedUrl
    }
}
