class DocumentationType{

    String description
    String type = 'Documentation'

    Set documents

    static hasMany = [documents:Documentation]
    static constraints = {
        description(nullable:false, blank:false)
        documents(nullable:true)
    }

    static mapping = {
        table 'R_CI_TYPE'
    }

    String toString(){
        return "${description}"
    }
}