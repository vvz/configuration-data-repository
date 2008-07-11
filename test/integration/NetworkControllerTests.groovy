class NetworkControllerTests extends GroovyTestCase {
    public void testIndex() {
        def controller = new NetworkController()
        controller.index()
        assertEquals "/network/list", controller.response.redirectedUrl
    }
}
