class HardwareControllerTests extends GroovyTestCase
{
    def testEnvironment
    def solutions
    void setUp(){
        def aps = new Project(name:"APS", description:"APS project", ownerName:"Steve Holmes", ownerEmail:"sholmes@delegata.com").save(flush:true)
        testEnvironment = new Environment(name:"Testing", description:"Testing", ownerName:"Steve Holmes", ownerEmail:"sholmes@delegata.com", project:aps).save(flush:true)
        def hardwareType = new HardwareType(description:'Server', order:1)
        assert hardwareType.validate()
        hardwareType.save(flush:true)
        solutions = new Hardware(name:'Solutions',author:'Steve Holmes', hardwareType:hardwareType).save(flush:true)
        testEnvironment.addToConfigurationItems(solutions).save(flush:true)
    }
    public void testIndex(){
        def controller = new HardwareController()
        controller.index()
        assertEquals "/hardware/list", controller.response.redirectedUrl
    }

    public void testDelete(){
        def controller = new HardwareController()
        controller.params.id = solutions.id
        assert testEnvironment.configurationItems.size() == 1
        testEnvironment.configurationItems.each {assert it == solutions}
        controller.delete()
        assert testEnvironment.configurationItems.size() == 0
    }

    /*void tearDown(){
        Project.list()*.delete()
        Environment.list()*.delete()
        Hardware.list()*.delete()
    }*/
}
