hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'org.hibernate.cache.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
            pooled = false
            driverClassName = "org.hsqldb.jdbcDriver"
            username = "sa"
            password = ""
            dbCreate = "create-drop" // one of 'create', 'create-drop','update'
            url = "jdbc:hsqldb:mem:devDB"
        }
    }
    test {
        dataSource {
            pooled = false
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
            jndiName = "java:comp/env/jdbc/cdr"
        }
    }
}