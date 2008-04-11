class StatusReferenceTests extends GroovyTestCase
{
    public void testQuery(){
        def statusReferences = [new StatusReference(name:'aps',order:1),new StatusReference(name:'cdr',order:2)]
        statusReferences*.save(flush:true)
        assertEquals 2, StatusReference.list().size()
    }
}
