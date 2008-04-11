class RelationReferenceTests extends GroovyTestCase{
    public void testQuery(){
        def relationReferences = [new RelationReference(name:'aps',order:'1'),new RelationReference(name:'cdr',order:'2')]
        relationReferences*.save(flush:true)
        assertEquals 2, RelationReference.list().size()
    }
}
