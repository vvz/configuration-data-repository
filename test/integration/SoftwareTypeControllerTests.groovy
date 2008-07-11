class SoftwareTypeControllerTests extends GroovyTestCase {
    public void testIndex() {
        def controller = new SoftwareTypeController()
        controller.index()
        assertEquals "/softwareType/list", controller.response.redirectedUrl
    }
}
