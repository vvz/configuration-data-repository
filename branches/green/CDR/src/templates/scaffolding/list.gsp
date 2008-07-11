<%=packageName%>  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>${className} List</title>
    </head>
    <body>
        <div class="body">
            <h1>${className} List</h1>
            <div class="nav">%{----}%
                %{--<span class="menuButton"><a class="home" href="\${createLinkTo(dir:'')}">Home</a></span>--}%
                <span class="menuButton"><g:link class="create" action="create">New ${className}</g:link></span>
            </div>
            <g:if test="\${flash.message}">
            <div class="message">\${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        <%  props = domainClass.properties.findAll { it.name != 'version' && it.type != Set.class }
                            Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
                            props.eachWithIndex { p,i ->
                                if(i == 0) {

                                }else if(i < 6) {
                   	                if(p.isAssociation()) { %>
                   	        <th>${p.naturalName}</th>
                   	    <%          } else { %>
                   	        <g:sortableColumn property="${p.name}" title="${p.naturalName}" />
                        <%  }   }   } %>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="\${${propertyName}List}" status="i" var="${propertyName}">
                        <tr class="\${(i % 2) == 0 ? 'odd' : 'even'}">
                        <%  props.eachWithIndex { p,i ->
                                if(i == 0) { %>
                            %{--<td><g:link action="show" id="\${${propertyName}.id}">\${${propertyName}.${p.name}?.encodeAsHTML()}</g:link></td>--}%
                        <%      } else if(i == 1) { %>
                            <td><g:link action="show" id="\${${propertyName}.id}">\${${propertyName}.${p.name}?.encodeAsHTML()}</g:link></td>
                        <%      } else if(i < 6) { %>
                            <td>\${${propertyName}.${p.name}?.encodeAsHTML()}</td>
                        <%  }   } %>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="\${${className}.count()}" />
            </div>
        </div>
    </body>
</html>
