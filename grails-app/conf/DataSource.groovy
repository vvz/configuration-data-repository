dataSource {
    pooled = false
    driverClassName = "org.hsqldb.jdbcDriver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='org.hibernate.cache.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop','update'
            url = "jdbc:hsqldb:mem:devDB"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            driverClassName = "oracle.jdbc.OracleDriver"
            username = "cdrtest"
            password = "cdrtest"
            url = "jdbc:oracle:thin:@//192.168.0.235:1521/COAPS"
            
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            driverClassName = "oracle.jdbc.OracleDriver"
            username = "cdrtrain"
            password = "cdrtrain"
            url = "jdbc:oracle:thin:@192.168.0.235:1521:COAPS"
        }
    }

    /*production {
        dataSource {
            dbCreate = "create-drop"
            driverClassName = "oracle.jdbc.OracleDriver"
            username = "cdrint"
            password = "cdrint"
            url = "jdbc:oracle:thin:@192.168.0.235:1521:COAPS"
        }
    }

    production {
        dataSource {
            dbCreate = "update"
            driverClassName = "oracle.jdbc.OracleDriver"
            username = "cdrprod"
            password = "cdrprod"
            url = "jdbc:oracle:thin:@192.168.0.235:1521:COAPS"
        }
    }*/
}