class DocumentationControllerTests extends GroovyTestCase{
  public void testIndex() {
        def controller = new DocumentationController()
        controller.index()
        assertEquals "/documentation/list", controller.response.redirectedUrl
    }
}
