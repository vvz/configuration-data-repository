class HardwareTypeControllerTests extends GroovyTestCase{
  public void testIndex(){
        def controller = new HardwareTypeController()
        controller.index()
        assertEquals "/hardwareType/list", controller.response.redirectedUrl
    }
}
