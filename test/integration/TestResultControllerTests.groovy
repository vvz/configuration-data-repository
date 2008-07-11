class TestResultControllerTests extends GroovyTestCase{
  public void testIndex() {
        def controller = new TestResultController()
        controller.index()
        assertEquals "/testResult/list", controller.response.redirectedUrl
    }
}
