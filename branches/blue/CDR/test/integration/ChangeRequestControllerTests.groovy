class ChangeRequestControllerTests extends GroovyTestCase {
    //class ChangeRequestControllerTests extendzzz GroovyTestCase {   //compile error
    public void testIndex() {
        def controller = new ChangeRequestController()
        controller.index()
        assertEquals "/changeRequest/list", controller.response.redirectedUrl
        //assertEquals "this", "that" //test failure
        //assertEquals "this", "this" //legitimate change to test, should work
        println "Hitesh"
        println "Donnette"

    }
}
