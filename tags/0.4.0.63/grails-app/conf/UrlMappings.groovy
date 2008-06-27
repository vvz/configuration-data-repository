class UrlMappings {
    static mappings = {
        /*"/hardwares/$id?"{
            controller = 'hardware'
            action = [GET:'showRest', PUT:"updateRest", DELETE:"deleteRest", POST:"saveRest"]
        }*/
        "/"(controller: "project")

        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }
    }
}