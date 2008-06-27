class ChangeRequestControllerTests extends GroovyTestCase{
  public void testIndex() {
        def controller = new ChangeRequestController()
        controller.index()
        assertEquals "/changeRequest/list", controller.response.redirectedUrl
    }
}
